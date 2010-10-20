require 'rubygems'
require 'sinatra'
require 'config/init'
Dir['routes/*'].each { |obj| require obj }

configure do
  API_KEY = 5500
  OBJECTS = ['user', 'point', 'trail', 'condition', 'category']
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

helpers do
  def password_matches_user?(user, pass)
    User.all(:email => user, :pwhash => pass).empty?
  end  
end

get '/' do
  "Welcome to mobile trail mapping application"
end


