require 'pp'

get '/point/get' do
  @trails = Trail.all 
  @misc = Point.all(Point.trail.id => -1)
  
  builder :point
end

post '/point/add' do
  point = Point.first_or_create(:lat => params[:lat], :long => params[:long], :desc => params[:desc], :title => params[:title])
  #not sure why you have to set these to variables first, but you do
  cat = Category.first_or_create(:name => params[:category])
  cond = Condition.first_or_create(:desc => params[:condition])
  trail = Trail.first_or_create(:name => params[:trail])
  point.category = cat
  point.condition = cond
  point.trail = trail

  params[:connections].split(',').each do |conn|
    point.connections << Connection.first_or_create(:connection => conn.to_i)
  end

  point.save

  "Added Point #{point.lat}, #{point.long}"
end

