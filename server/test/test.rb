require 'rubygems'
require '/Users/davidpick/Documents/Mobile-Trail-Mapping/server/lib/server'
require 'rack/test'
require 'test/unit'

class ServerTest < Test::Unit::TestCase
  include Rack::Test::Methods

  def setup
    @base_url = "/5500"
    @test_user = "/test@brousalis"
  end

  def app
    Sinatra::Application
  end

  def test_default_route
    get '/'
    assert_equal 'Welcome to mobile trail mapping application', last_response.body
  end

  def test_invalid_api_key
    get "123/anon/point/get/none"
    assert_equal 'Invalid API Key', last_response.body
  end

  def test_get_point
    get @base_url + "/anon/point/get/none"
    assert_equal 'test point', last_response.body
  end

  def test_add_point
    put @base_url + @test_user + "/point/put/none" 
  end

  def test_add_point_invalid_user
    put @base_url + '/unkown/point/put/none'
  end

  def test_add_point_invalid_api
    put "/2341213" + @test_user + "/point/put/none"
  end
end
