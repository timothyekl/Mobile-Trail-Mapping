require File.join(File.dirname(__FILE__), '..', 'server.rb')
require 'rack/test'
require 'test/unit'
require 'digest/sha1'

set :environment, :test
set :run, false
set :raise_errors, true
set :logging, false
