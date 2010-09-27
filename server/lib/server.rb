require 'rubygems'
require 'sinatra'
require 'sequel'
#require all models


configure do
  API_KEY = 5500
  OPT_DELIMITER = '|'

  #connect to db
  DB = Sequel.sqlite('test.db')
  DB[:user].insert(:email => 'test@brousalis', :pwhash => 1234)
end

Dir[File.dirname(__FILE__) + '/server/*'].each { |model| require model }

helpers do
  def parse_opts(opts)
    split = opts.split(OPT_DELIMITER) 
    opts_hash = Hash.new
    split.each do |opt|
      temp = opt.split('=')
      opts_hash[temp[0]] = temp[1].to_i
    end

    return opts_hash
  end

  def user_exists?(user)
    return DB[:user].filter(:email => user).empty?
  end
end

get '/' do
  return "Welcome to mobile trail mapping application"
end

get '/:api_key/anon/point/get/:opts' do
  return 'Invalid API Key' if params[:api_key].to_i != API_KEY
  opts = parse_opts(params[:opts])

  return 'test point'
end

put '/:api_key/:user/point/put/:opts' do
  return 'Must be an admin for this action' if user_exists?(params[:user])
  return 'Invalid API Key' if params[:api_key].to_i != API_KEY
end
