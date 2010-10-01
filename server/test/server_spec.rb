require File.dirname(__FILE__) + '/spec_helper'

describe "Server Tests" do
  include Rack::Test::Methods

  def app
    @app ||= Sinatra::Application
  end

  before :all do
    @objects = ['user', 'point', 'trail', 'condition', 'catagory']
    @base_response = 'Welcome to mobile trail mapping application'
    @api_key = 5500
    @test_user = "test@brousalis.com"
    @test_pw = Digest::SHA1.hexdigest('password')
    @invalid_user = "invalid@brousalis.com"
    User.first_or_create(:email => 'test@brousalis.com', :pwhash => Digest::SHA1.hexdigest('password')).save
  end

  describe "base actions" do
    it "should respond to /" do
      get '/'
      last_response.body.should == @base_response
    end

    it "should error for an invalid api key" do
      @objects.each do |object|
        post "/#{object}/add", {:api_key => 12345}
        last_response.body.should == 'Invalid API Key'
      end
    end
  end

  describe "Point Actions" do
    it "should return a test point" do
      pending("haven't decided xml structure yet")
      params = {:api_key => @api_key,
                :user => @test_user,
                :pwhash => @test_pw }

      get "/point/get", params
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
                :api_key => @api_key,
                :desc => 'test'}

      post "/point/add", params
      last_response.body.should == "Added Point 4, 5"
    end

    it "should catch an invalid user" do
      post '/point/add', {:user => @invalid_user, :pwhash => @test_pw, :api_key => @api_key}
      last_response.body.should == 'Invalid username or password'
    end
  end

  describe "Trail Actions" do
    it "should add a trail" do
      trailname = 'trail'
      params = {:trail => trailname,
                :api_key => @api_key,
                :user => @test_user,
                :pwhash => @test_pw }

      post '/trail/add', params
      last_response.body.should == "Added Trail #{trailname}"
      Trail.first(:name => trailname).name.should == trailname
    end

    it "should error for an invalid user" do
      params = {:trail => 'trail',
                :api_key => @api_key,
                :user => @invalid_user,
                :pwhash => @test_pw }

      post '/trail/add', params
      last_response.body.should == "Invalid username or password"   
    end
  end

  describe "Catagory Actions" do
    it "should add a catagory" do
      catagoryName = 'catagory'

      params = {:catagory => catagoryName,
                :api_key => @api_key,
                :user => @test_user,
                :pwhash => @test_pw }

      post '/catagory/add', params
      last_response.body.should == "Added Catagory #{catagoryName}"
      Catagory.first(:name => catagoryName).name.should == catagoryName
    end

    it "should error for an invalid user" do
      params = {:catagory => 'catagory',
                :api_key => @api_key,
                :user => @invalid_user,
                :pwhash => @test_pw }

      post '/catagory/add', params
      last_response.body.should == "Invalid username or password"   
    end
  end

  describe "Condition Actions" do
    it "should add a condition" do
      condition = 'condition'
      params = {:condition => condition,
                :api_key => @api_key,
                :user => @test_user,
                :pwhash => @test_pw }

      post '/condition/add', params
      last_response.body.should == "Added Condition #{condition}"
      Condition.first(:desc => condition).desc.should == condition
    end

    it "should error for an invalid user" do
      params = {:name => 'condition',
                :api_key => @api_key,
                :user => @invalid_user,
                :pwhash => @test_pw }

      post '/condition/add', params
      last_response.body.should == "Invalid username or password"   
    end

    it "should get a condition" do
      pending("haven't decided on xml structure yet")
      Condition.first_or_create(:desc => 'condition')

      params = {:api_key => @api_key,
                :user => @test_user,
                :pwhash => @test_pw }

      get '/condition/get', params
      last_response.body.should == "condition"
    end
  end
end
