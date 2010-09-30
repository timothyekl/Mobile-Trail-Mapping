class Trail < Sequel::Model
  one_to_many :point
end
