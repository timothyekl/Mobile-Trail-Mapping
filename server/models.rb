class Category
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
  property :path, String
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

  property :id, Serial
  property :lat,  Float, :required => true
  property :long, Float, :required => true
  property :desc, Text
  property :title, String

  belongs_to  :category
  belongs_to  :condition
  belongs_to  :trail
  has n, :photos
  has n, :connections
end

class Connection
  include DataMapper::Resource

  property :id, Serial
  property :connected_to, Integer, :required => true
  property :connected_from, Integer, :required => true

  belongs_to :point
end

class User
  include DataMapper::Resource

  property :id,     Serial
  property :email,  String, :required => true
  property :pwhash, String, :required => true
end
