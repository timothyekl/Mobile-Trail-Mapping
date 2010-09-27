class Photo < Sequel::Model
  DB.create_table? :photo do
    primary_key :id
    text :desc
  end
end
