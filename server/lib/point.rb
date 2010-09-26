require 'sinatra/sequel'

class Point < Sequel::Model
  unless table_exists?
    set_schema do
      primary_key :id
      int :x
      int :y
      int :next
      int :prev
      text :desc
      many_to_one :catagory
      one_to_many :photo
    end
    create_table
  end
end
