require 'pp'
require 'net/http'
require 'uri'

File.open('iowa.txt', 'r') do |in_file|
  count = 1
  points = [] 
  more_conns_ids = {}
  connections = {}

  while (line = in_file.gets)
    parts = line.split(" ").map { |part| part.chomp }

    if parts[0][0] == 42 #42 is *
      lat = parts[1]
      long = parts[2]

      if more_conns_ids.keys.include?(parts[0][1])
        connections[count] = more_conns_ids[parts[0][1]]
        pp connections
      else
        puts "adding a second connection"
        more_conns_ids[parts[0][1]] = count
        pp more_conns_ids
      end
    else
      lat = parts[0]
      long = parts[1]
    end

    points << { :lat => lat, :long => long, :id => count, :connections => "#{count - 1 }"} if count > 0
    points << { :lat => lat, :long => long, :id => count, :connections => "" } if count == 0

    count += 1
  end

  connections.keys.each do |connection|
    points[connection][:connections] << ", #{connections[connection]}"
  end

  points.each do |point|
    point[:desc] = "point"
    point[:category] = "open"
    point[:condition] = "clear"
    point[:trail] = "iowa"
  end

  points.each do |point|
    Net::HTTP.post_form(URI.parse('http://localhost:4567/point/add'), point)
  end
end
