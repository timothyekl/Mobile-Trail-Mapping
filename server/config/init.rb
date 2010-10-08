require 'rubygems'
require 'dm-core'
require 'dm-migrations'
require 'digest/sha1'
require 'builder'

configure :development do
  DataMapper.setup(:default, 'sqlite://' + Dir.pwd + '/tmp/development.db' )
end

configure :test do
  DataMapper.setup(:default, 'sqlite::memory:')
end

require 'models'

DataMapper.finalize
DataMapper.auto_upgrade!


