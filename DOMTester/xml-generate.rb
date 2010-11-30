File.open("points.xml", 'w') do |out_file|
  out_file.write('<?xml version="1.0" encoding="ISO-8859-1"?><data><trails><trail id="0" name="Heritage"><points>\n')

  in_file = File.new("points.txt", 'r')
  count = 0

  while (line = in_file.gets)
    parts = line.split(" ").map { |part| part.chomp }
    lat = parts[0]
    long = parts[1]

    out_file.write("<point id='#{count}'><title>Point #{count}</title><description>Point #{count}</description><category id="0">Category</category><condition>Open</condition><latitude>#{lat}</latitude><longitude>#{long}</longitude>")
  end
end
