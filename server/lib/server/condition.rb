class Condition < Sequel::Model
  DB.create_table? :condition do
    primary_key :id
    text :desc
  end
end
