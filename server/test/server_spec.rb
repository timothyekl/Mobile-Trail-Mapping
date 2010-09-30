require File.dirname(__FILE__) + '/spec_helper'

describe "Server Tests" do
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
    User.destroy
    Point.destroy
    Catagory.destroy
    Trail.destroy
  end

  describe "base actions" do
    it "should respond to /" do
      get '/'
      last_response.body.should == @base_response
    end

    it "should error for an invalid api key" do
      ['user', 'point'].each do |object|
        post "/12345/#{object}/add"
        last_response.body.should == 'Invalid API Key'
      end
    end
  end

  describe "Point Actions" do
    it "should return a test point" do
      get @base_url + "/point/get"
      last_response.body.should == 'test point'
    end

    it "should add a point" do
      params = {:user => @test_user,
                :pwhash => @test_pw,
                :lat => 4,
                :long => 5,
                :connections => "",
                :catagory => 'test',
                :trail => 'test',
                :desc => 'test'}

      post @base_url + "/point/add", params
      last_response.body.should == "added point 4, 5"
    end

    it "should catch an invalid user" do
      post @base_url + '/point/add', {:user => @invalid_user, :pwhash => @test_pw}
      last_response.body.should == 'Invalid username or password'
    end
  end

  describe "Trail Actions" do
    it "should add a trail" do
      pending('Add a Trail')
      trailname = 'trail'
      params = {:name => trailname,
                :user => @test_user,
                :pshash => @test_pw }

      post @base_url + '/trail/add', params
      Trail.first(:name => trail).name.should == trailname
      last_response.body.should == "added trail #{trailname}"
    end

    it "should error for an invalid user" do
      pending('Add a trail with an invalid user')
      params = {:name => 'trail',
                :user => @invalid_user,
                :pshash => @test_pw }

      post @base_url + '/trail/add', params
      last_response.body.should == "Invalid username or password"   
    end
  end
end
