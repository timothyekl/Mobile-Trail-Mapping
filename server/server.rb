require 'rubygems'
require 'sinatra'
require 'sinatra/sequel'
#require all models
Dir['lib/*'].each { |model| require model }

get '/' do
  return "Welcome to mobile trail mapping application"
end


