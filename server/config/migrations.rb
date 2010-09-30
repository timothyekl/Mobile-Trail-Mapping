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
    integer :point_id
    text :desc
  end

  database.create_table :trails do
    primary_key :id
    text :name
  end

  database.create_table :points do
    primary_key :id
    integer :lat
    integer :long
    text :desc
    integer :catagory_id
    integer :trail_id
  end

  database.create_table :points_points do
    primary_key :id
    integer :id_left
    integer :id_right
  end

  database.create_table :users do
    primary_key :id
    text :email, :unique => true, :null => false
    text :pwhash
  end
end
