xml.instruct!
xml.conditions do
  @trails.each do |trail|
    xml.trail do
      xml.trail.points.each do |point|
        xml.lat point[:lat]
        xml.long point[:long]
        xml.desc point[:desc]
      end
    end
  end
end
