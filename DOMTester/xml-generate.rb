require 'pp'

def write_point(lat, long, id, file)
    file.write("<point id='#{id}'><title>Point #{id}</title><description>Point #{id}</description><category id='0'>Category</category><condition>Open</condition><latitude>#{lat}</latitude><longitude>#{long}</longitude>")
end

def write_connection(file, id)
  file.write("<connections><connection>#{id}</connection></connections>")
end

def write_header(file)
  file.write('<?xml version="1.0" encoding="ISO-8859-1"?><data><trails><trail id="0" name="Heritage"><points>\n')
end

def close_tags(file)
  file.write("</points></trail></trails><misc></misc></data>\n")
end

def close_point_tag(file)
  file.write("</point>\n")
end

File.open("points.xml", 'w') do |out_file|
  write_header(out_file)

  in_file = File.new("points.txt", 'r')
  count = 0
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

    points << { :lat => lat, :long => long, :id => count, :connections => [count - 1] } if count > 0
    points << { :lat => lat, :long => long, :id => count, :connections => [] } if count == 0

    count += 1
  end

  connections.keys.each do |connection|
    points[connection][:connections] << connections[connection]
  end

  points.each do |point|
    write_point(point[:lat], point[:long], point[:id], out_file)
    point[:connections].each do |connection|
      write_connection(out_file, connection)
    end
  end

  close_point_tag(out_file)
  close_tags(out_file)
end


