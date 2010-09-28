require 'rubygems'
require 'sinatra'
require 'config/init'
require 'digest/sha1'

configure do
  API_KEY = 5500
  OPT_DELIMITER = '|'
  User.new(:email => 'test@brousalis.com', :pwhash => Digest::SHA1.hexdigest('password'))
end

get '/' do
  "Welcome to mobile trail mapping application"
end

get '/:api_key/point/get/:opts' do
  return 'Invalid API Key' if params[:api_key].to_i != API_KEY
  opts = parse_opts(params[:opts])

  'test point'
end

post '/:api_key/user/add/:opts' do
  return 'Invalid API KEY' if params[:api_key].to_i != API_KEY

  user = User.new(:email => params[:email], :pwhash => params[:pwhash]).save
  return "Added user #{user.email}"
end

post '/:api_key/point/add/:opts' do
  return 'Invalid username or password' if password_matches_user?(params[:user], params[:pwhash])
  return 'Invalid API Key' if params[:api_key].to_i != API_KEY

  'added point'
end
