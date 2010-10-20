post '/condition/add' do
  condition = Condition.first_or_create(:desc => params[:condition])
  "Added Condition #{condition.desc}"
end

get '/condition/get' do
  content_type 'application/xml', :charset => 'utf-8'
  @conditions = Condition.all
  builder :condition
end
