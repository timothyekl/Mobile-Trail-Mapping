class Point < Sequel::Model
  DB.create_table? :point do
    primary_key :id
    int :lat
    int :long
    int :next
    int :prev
    text :desc
    many_to_one :catagory
    one_to_many :photo
  end
end
