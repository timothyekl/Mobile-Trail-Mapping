require File.join(File.dirname(__FILE__), '..', 'server.rb')
require 'rack/test'
require 'spec'
require 'spec/autorun'
require 'spec/interop/test'

set :environment, :development
set :run, false
set :raise_errors, true
set :logging, false
set :show_exceptions, false
