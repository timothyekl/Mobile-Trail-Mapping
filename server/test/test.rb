require 'rubygems'
require '/Users/davidpick/Documents/Mobile-Trail-Mapping/server/lib/server'
require 'rack/test'
require 'test/unit'

class ServerTest < Test::Unit::TestCase
  include Rack::Test::Methods

  def setup
    @base_url = "/5500"
    @test_user = "test@brousalis.com"
    @invalid_user = "invalid@brousalis.com"
  end

  def app
    Sinatra::Application
  end

  def test_default_route
    get '/'
    assert_equal 'Welcome to mobile trail mapping application', last_response.body
  end

  def test_invalid_api_key
    get "123/point/get/none"
    assert_equal 'Invalid API Key', last_response.body
  end

  def test_get_point
    get @base_url + "/point/get/none"
    assert_equal 'test point', last_response.body
  end

  def test_add_point
    post @base_url + "/point/put/none", {:user => @test_user} 
    assert_equal 'added point', last_response.body
  end

  def test_add_point_invalid_user
    post @base_url + '/point/put/none', {:user => @invalid_user}
    assert_equal 'Must be an admin for this action', last_response.body
  end

  def test_add_point_invalid_api
    post "/2341213/point/put/none", {:user => @test_user}
    assert_equal 'Invalid API Key', last_response.body
  end
end
