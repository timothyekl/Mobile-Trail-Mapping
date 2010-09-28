migration "create the tables" do
  database.create_table :catagories do
    primary_key :id
    text :name, :unique => true, :null => false
  end

  database.create_table :conditions do
    primary_key :id                             
    text :desc, :unique => true, :null => false
  end

  database.create_table :photos do
    primary_key :id
    text :desc
  end

  database.create_table :points do
    primary_key :id
    integer :lat
    integer :long
    integer :next
    integer :prev
    text :desc
    many_to_one :catagory
    one_to_many :photo
  end

  database.create_table :users do
    primary_key :id
    text :email, :unique => true, :null => false
    text :pwhash
  end
end
