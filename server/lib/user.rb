require 'sinatra/sequel'

class User < Sequel::Model
  unless table_exists?
    set_schema do
      primary_key :id
      text :first_name
      text :last_name
      text :email
    end
  end
end
