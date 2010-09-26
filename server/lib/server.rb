require 'pp'
require 'rubygems'
require 'sinatra'
require 'sinatra/sequel'
#require all models
Dir['lib/*'].each { |model| require model }

API_KEY = 5500
OPT_DELIMITER = '|'

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
end

get '/' do
  return "Welcome to mobile trail mapping application"
end

get '/:api_key/anon/point/get/:opts' do
  return 'Invalid API Key' if params[:api_key].to_i != API_KEY
  opts = parse_opts(params[:opts])


  return 'test point'
end


