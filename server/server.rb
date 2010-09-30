require 'pp'
require 'rubygems'
require 'sinatra'
require 'config/init'
require 'digest/sha1'

configure do
  API_KEY = 5500
end

before do
  if params.keys.include?("api_key")
    halt 'Invalid API Key' if params[:api_key].to_i != API_KEY
  end

  if params.keys.include?("user") and params.keys.include?("pwhash") 
    halt 'Invalid username or password' if password_matches_user?(params[:user], params[:pwhash])
  end
end

get '/' do
  "Welcome to mobile trail mapping application"
end

#User Routes
post '/user/add' do
  user = User.create(:email => params[:email], :pwhash => params[:pwhash])
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

  "added point #{point.lat}, #{point.long}"
end
