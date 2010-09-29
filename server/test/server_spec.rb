require File.dirname(__FILE__) + '/spec_helper'

describe "Base Actions" do
  include Rack::Test::Methods

  def app
    @app ||= Sinatra::Application
  end

  before :all do
    @base_response = 'Welcome to mobile trail mapping application'
    @base_url = "/5500"
    @test_user = "test@brousalis.com"
    @test_pw = Digest::SHA1.hexdigest('password')
    @invalid_user = "invalid@brousalis.com"
  end

  before :each do
    User.new(:email => 'test@brousalis.com', :pwhash => Digest::SHA1.hexdigest('password')).save
  end

  after :each do
    User.delete
  end

  it "should respond to /" do
    get '/'
    last_response.body.should == @base_response
  end
end


describe "Point Actions" do
  include Rack::Test::Methods

  def app
    @app ||= Sinatra::Application
  end

  before :all do
    @base_response = 'Welcome to mobile trail mapping application'
    @base_url = "/5500"
    @test_user = "test@brousalis.com"
    @test_pw = Digest::SHA1.hexdigest('password')
    @invalid_user = "invalid@brousalis.com"
  end

  before :each do
    User.new(:email => 'test@brousalis.com', :pwhash => Digest::SHA1.hexdigest('password')).save
  end

  after :each do
    User.delete
  end

  it "should error for an invalid api key" do
    get "123/point/get"
    last_response.body.should == 'Invalid API Key'
  end

  it "should return a test point" do
    get @base_url + "/point/get"
    last_response.body.should == 'test point'
  end

  it "should add a point" do
    pending("implement get correctly")
    post @base_url + "/point/add", {:user => @test_user, :pwhash => @test_pw, :lat => 4, :long => 5} 
    last_response.body.should == Point.new(:email => @test_user, :pwhash => @test_pw, :lat => 4, :long => 5)
  end

  it "should catch an invalid user" do
    post @base_url + '/point/add', {:user => @invalid_user, :pwhash => @test_pw}
    last_response.body.should == 'Invalid username or password'
  end

  it "should error for an invalid api key on adding a point" do
    post "/2341213/point/add", {:user => @test_user, :pwhash => @test_pw}
    last_response.body.should == 'Invalid API Key'
  end
end
