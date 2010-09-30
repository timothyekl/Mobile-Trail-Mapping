require 'pp'
require 'sinatra/sequel'
require 'sqlite3'

configure :development do
  set :database, 'sqlite://tmp/development.sqlite'
end

configure :test do
  set :database, 'sqlite3::memory:'
end

require 'config/migrations'

Sequel::Model.strict_param_setting = false
Dir["models/**/*.rb"].each { |model| require model }

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
    User.filter(:email => user, :pwhash => pass).empty?
  end  

  def addCatagory(catagory)
    return Catagory.first(:name => catagory) if !Catagory.filter(:name => catagory).empty?
    Catagory.create(:name => catagory) 
  end

  def addTrail(trail)
    return Trail.first(:name => trail) if !Trail.filter(:name => trail).empty?
    Trail.create(:name => trail) 
  end
end
