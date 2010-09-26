require 'sinatra/sequel'

class Photo < Sequel::Model
  unless table_exists?
    set_schema do
      primary_key :id
      text :desc
    end
    create_table
  end
end
