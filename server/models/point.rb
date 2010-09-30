class Point < Sequel::Model
  many_to_one :catagory
  many_to_one :trail
  one_to_many :photo
  many_to_many :direct_successors, :left_key=>:id_left,
      :right_key=>:id_right, :join_table=>:points_points, :class=>self
  many_to_many :direct_successors, :right_key=>:id_left,
      :left_key=>:id_right, :join_table=>:points_points, :class=>self
end
