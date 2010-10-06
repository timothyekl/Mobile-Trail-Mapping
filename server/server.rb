require 'rubygems'
require 'sinatra'
require 'config/init'

configure do
  API_KEY = 5500
  OBJECTS = ['user', 'point', 'trail', 'condition', 'category']
  User.first_or_create(:email => 'test@brousalis.com', :pwhash => Digest::SHA1.hexdigest('password'))

  set :logging, true
  DataMapper::Logger.new(STDOUT, :debug) 
end

before do
  OBJECTS.each do |object|
    if request.path_info.split('/').include?(object)
      halt 'Invalid API Key' if params[:api_key].to_i != API_KEY

      halt "Invalid username or password" if password_matches_user?(params[:user], params[:pwhash])
    end
  end
end

get '/' do
  "Welcome to mobile trail mapping application"
end

#User Routes
post '/user/add' do
  user = User.create(:email => params[:user], :pwhash => params[:pwhash])
  return "Added user #{user.email}"
end

#Point Routes
post '/point/add' do
  point = Point.first_or_create(:lat => params[:lat], :long => params[:long], :desc => params[:desc])
  #not sure why you have to set these to variables first, but you do
  cat = Category.first_or_create(:name => params[:category])
  cond = Condition.first_or_create(:desc => params[:condition])
  trail = Trail.first_or_create(:name => params[:trail])
  point.category = cat
  point.condition = cond
  point.trail = trail

  params[:connections].split(',').each { |p| point.connections << Point.get(p.to_i) }

  point.save

  "Added Point #{point.lat}, #{point.long}"
end

post '/condition/add' do
  condition = Condition.first_or_create(:desc => params[:condition])
  "Added Condition #{condition.desc}"
end

get '/condition/get' do
  content_type 'application/xml', :charset => 'utf-8'
  @conditions = Condition.all
  builder :condition
end

post '/category/add' do
  category = Category.first_or_create(:name => params[:category])
  "Added Category #{category.name}"
end

get '/category/get' do

end

post '/trail/add' do
  trail = Trail.first_or_create(:name => params[:trail])
  "Added Trail #{trail.name}"
end
