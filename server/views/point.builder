xml.instruct!
xml.data do
  @trails.each do |trail|
    xml.trail do
      trail.points.each do |point|
        xml.title point.title
        xml.description point.desc
        xml.catagory point.catagory.name
        xml.condition point.condition.desc
        xml.latitude point.lat
        xml.longitude point.long
        point.connections.each do |conn|
          xml.connection conn.target_id
        end
      end
    end
  end
  @misc.each do |point|
    xml.title
    xml.description
    xml.catagory
    xml.condition
    xml.latitude
    xml.longitude
    point.connections.each do |conn|
      xml.connection
    end
  end
end
