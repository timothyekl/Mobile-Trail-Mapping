#!/usr/bin/env python3.1

import hashlib
import urllib.request
import urllib.parse
import time

lines = []
with open('iowa.txt', 'r') as f:
	for line in f:
		#lines.insert(0, line.replace("\n", ""))
		lines.append(line.replace("\n", ""))

pwhash = "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8"

lastlat = None
lastlong = None
connDict = {}
for i in range(len(lines)):
	parts = lines[i].split(" ")
	
	if parts[0][0] == '*':
		connKey = parts[0][1:]
		latitude = "{:.6f}".format(float(parts[1]))
		longitude = "{:.6f}".format(float(parts[2]))
	else:
		latitude = "{:.6f}".format(float(parts[0]))
		longitude = "{:.6f}".format(float(parts[1]))
	
	connections = ""
	if lastlat != None and lastlong != None:
		connections = connections + "{}:{}".format(lastlat, lastlong)
		if parts[0][0] == '*' and connKey in connDict:
			connections = connections + ",{}:{}".format(connDict[connKey][0], connDict[connKey][1])
	
	data = {
		'user':'test@brousalis.com',
		'pwhash':pwhash,
		'title':'Trail point',
		'lat':latitude,
		'long':longitude,
		'connections':connections,
		'condition':'Open',
		'category':'test',
		'trail':'trail',
		'api_key':5500,
		'desc':'Trail point'
	}
	
	print("Sending lat:{} long:{} conns:{}".format(latitude, longitude, connections))
	
	response = urllib.request.urlopen("http://localhost:4567/add/point/coords", urllib.parse.urlencode(data))
	for r in response:
		print(r)
	print("")
	time.sleep(1)
	
	if parts[0][0] == '*':
		connDict[connKey] = [latitude, longitude]
	
	lastlat = latitude
	lastlong = longitude
	