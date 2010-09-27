class User < Sequel::Model
  DB.create_table? :user do
    primary_key :id
    text :email
    text :pwhash
  end
end
