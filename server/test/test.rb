require 'rubygems'
require '/Users/davidpick/Documents/Mobile-Trail-Mapping/server/lib/server'
require 'rack/test'
require 'test/unit'

class ServerTest < Test::Unit::TestCase
  include Rack::Test::Methods

  def setup
    @api_key = 5500
  end

  def app
    Sinatra::Application
  end

  def test_default_route
    get '/'
    assert_equal 'Welcome to mobile trail mapping application', last_response.body
  end

  def test_invalid_api_key
    get "/#{@api_key}/anon/point/get/none"
    assert_equal 'test point', last_response.body
  end

  def test_get_point
    get "/#{@api_key}/anon/point/get/none"
    assert_equal 'test point', last_response.body
  end
end
