class Catagory < Sequel::Model
  DB.create_table? :catagory do
    primary_key :id
    text :name
  end
end
