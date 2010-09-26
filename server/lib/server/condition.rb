require 'sinatra/sequel'

class Condition < Sequel::Model
  unless table_exists?
    set_schema do
      primary_key :id
      text :desc
  end
end
