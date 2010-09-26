require 'sinatra/sequel'

class Catagory < Sequel::Model
  unless table_exists?
    set_schema do
      primary_key :id
      text :name
    end
  end
end
