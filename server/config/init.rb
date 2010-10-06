require 'rubygems'
require 'dm-core'
require 'dm-migrations'

configure :development do
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
end
