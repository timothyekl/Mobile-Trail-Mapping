require 'rubygems'
require 'sinatra'
require 'sequel'
require 'digest/sha1'

configure do
  API_KEY = 5500
  OPT_DELIMITER = '|'

  #connect to db
  DB = Sequel.sqlite('test.db')
  pw = Digest::SHA1.hexdigest('password')
  DB[:user].insert(:email => 'test@brousalis.com', :pwhash => pw)
end

Dir[File.dirname(__FILE__) + '/server/*'].each { |model| require model }

helpers do
  def parse_opts(opts)
    opts_hash = Hash.new
    opts.split(OPT_DELIMITER).each do |opt| 
      temp = opt.split('=')
      opts_hash[temp[0]] = temp[1].to_i
    end

    opts_hash
  end

  def password_matches_user?(user, pass)
    DB[:user].filter(:email => user, :pwhash => pass).empty?
  end
end

get '/' do
  "Welcome to mobile trail mapping application"
end

get '/:api_key/point/get/:opts' do
  return 'Invalid API Key' if params[:api_key].to_i != API_KEY
  opts = parse_opts(params[:opts])

  'test point'
end

post '/:api_key/point/add/:opts' do
  return 'Invalid username or password' if password_matches_user?(params[:user], params[:pwhash])
  return 'Invalid API Key' if params[:api_key].to_i != API_KEY

  Point.new {:lat => 1, :long => 2, :next => 1, :prev => 1, :desc => 'description'}  

  'added point'
end
