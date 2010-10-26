require 'rubygems'
require 'builder'
require 'pp'

xml = Builder::XmlMarkup.new( :target => $stdout, :indent => 2 )

connection = [{:connection => 1}]
condition = {:desc => 'open'}
category = {:id => 1, :name => 'category'}
points = [{:id => 1, :title => 'point', :desc => 'desc', :category => category, :condition => condition, :lat => 1, :long => 1, :connections => connection}]
trails = [{:id => 1, :name => 'trail', :points => points}]

xml.instruct!
xml.data do
  xml.trails do
    trails.each do |trail|
      xml.trail :id => trail[:id], :name => trail[:name] do
        xml.points do
          trail[:points].each do |point|
            xml.point :id => point[:id] do
              xml.title point[:title]
              xml.description point[:desc]
              xml.category point[:category][:name], :id => point[:category][:id]
              xml.condition point[:condition][:desc]
              xml.latitude point[:lat]
              xml.longitude point[:long]
              xml.connections do
                point[:connections].each do |conn|
                  xml.connection conn[:connection]
                end
              end
            end
          end
        end
      end
    end
  end

  xml.misc do
  end
end
