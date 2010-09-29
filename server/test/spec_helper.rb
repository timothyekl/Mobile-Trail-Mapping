require File.join(File.dirname(__FILE__), '..', 'server.rb')
require 'rack/test'
require 'spec'
require 'spec/autorun'
require 'spec/interop/test'

ENV['RACK_ENV'] = 'test'

set :environment, :test
set :run, false
set :raise_errors, true
set :logging, false
