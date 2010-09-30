require 'rubygems'
require 'dm-core'
require 'dm-migrations'

configure :development do
  puts File.dirname(__FILE__)
  DataMapper.setup(:default, 'sqlite://' + Dir.pwd + '/tmp/development.db' )
end

configure :test do
  DataMapper.setup(:default, 'sqlite::memory:')
end

require 'models'

DataMapper.finalize
DataMapper.auto_upgrade!

helpers do
  def password_matches_user?(user, pass)
    User.all(:email => user, :pwhash => pass).empty?
  end  

  def addCatagory(catagory)
    return Catagory.all(:name => catagory) if !Catagory.all(:name => catagory).empty?
    Catagory.create(:name => catagory) 
  end

  def addTrail(trail)
    return Trail.all(:name => trail) if !Trail.all(:name => trail).empty?
    Trail.create(:name => trail) 
  end
end
