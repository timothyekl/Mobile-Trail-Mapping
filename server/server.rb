require 'rubygems'
require 'sinatra'
require 'config/init'

configure do
  API_KEY = 5500
  OBJECTS = ['user', 'point', 'trail', 'condition', 'category']
  User.first_or_create(:email => 'test@brousalis.com', :pwhash => Digest::SHA1.hexdigest('password'))
end

OBJECTS.each { |obj| require "routes/#{obj}_routes" }

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
