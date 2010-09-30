class Catagory
  include DataMapper::Resource

  property :id,   Serial
  property :name, String, :required => true

  has n, :points
end

class Condition
  include DataMapper::Resource

  property :id,   Serial
  property :desc, Text, :required => true

  has n, :points
end

class Photo
  include DataMapper::Resource

  property :id,   Serial
  property :desc, Text

  belongs_to :point
end

class Trail
  include DataMapper::Resource

  property :id,   Serial
  property :name, String, :required => true

  has n, :points
end

class Point
  include DataMapper::Resource

  property :id,   Serial
  property :lat,  Integer, :required => true
  property :long, Integer, :required => true
  property :desc, Text

  belongs_to  :catagory
  belongs_to  :condition
  belongs_to  :trail
  has n, :photos  
end

class User
  include DataMapper::Resource

  property :id,     Serial
  property :email,  String, :required => true
  property :pwhash, String, :required => true
end
