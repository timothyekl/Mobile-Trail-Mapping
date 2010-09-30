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

  has n, :connections, :child_key => [ :source_id ]
  has n, :connected_points, self, :through => :connections, :via => :target
end

class Connection
  include DataMapper::Resource

  property :source_id, Integer, :key => true, :min => 1
  property :target_id, Integer, :key => true, :min => 1

  belongs_to :source, 'Point', :key => true
  belongs_to :target, 'Point', :key => true
end

class User
  include DataMapper::Resource

  property :id,     Serial
  property :email,  String, :required => true
  property :pwhash, String, :required => true
end
