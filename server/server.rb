require 'pp'
require 'rubygems'
require 'sinatra'
require 'config/init'
require 'digest/sha1'

configure do
  API_KEY = 5500
  OBJECTS = ['user', 'point', 'trail', 'condition', 'catagory']
  User.first_or_create(:email => 'test@brousalis.com', :pwhash => Digest::SHA1.hexdigest('password'))
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
get '/point/get' do
  'test point'
end

post '/point/add' do
  point = Point.new(:lat => params[:lat],
                    :long => params[:long],
                    :desc => params[:desc],
                    :catagory => Catagory.first_or_create(:name => params[:catagory]),
                    :trail => Trail.first_or_create(:name => params[:trail]))

  params[:connections].split(',').each { |p| point.connections << Point.get(p.to_i)}
  point.save

  "Added Point #{point.lat}, #{point.long}"
end

post '/condition/add' do
  condition = Condition.first_or_create(:desc => params[:condition])

  "Added Condition #{condition.desc}"
end
