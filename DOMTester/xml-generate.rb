File.open("points.xml", 'w') do |out_file|
  write_header(out_file)

  in_file = File.new("points.txt", 'r')
  count = 0

  while (line = in_file.gets)
    parts = line.split(" ").map { |part| part.chomp }
    lat = parts[0]
    long = parts[1]
    write_point(lat, long, count, out_file)
    write_connection(out_file, count - 1) if count > 0
    close_point_tag(out_file)
    count += 1
  end

  close_tags(out_file)
end

def write_point(lat, long, id, file)
    file.write("<point id='#{id}'><title>Point #{id}</title><description>Point #{id}</description><category id="0">Category</category><condition>Open</condition><latitude>#{lat}</latitude><longitude>#{long}</longitude>")
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
