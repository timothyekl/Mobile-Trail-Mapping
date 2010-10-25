#!/usr/bin/env python3.1

'''
Python script to generate a schema-compliant XML file from another file
containing a set of points. Input file must have one point per line, defined
as latitude and longitude values separated by a single space. There can be
no blank lines in the file. Sample input file:

39.485189 -87.295395
39.485582 -87.300839
39.485723 -87.303253

Input file must be called points.txt; resulting generated output is points.xml.
'''

with open("points.xml", 'w') as out:
    out.write('<?xml version="1.0" encoding="ISO-8859-1"?><data><trails><trail id="0" name="Heritage"><points>\n')
    with open("points.txt") as f:
        i = 0
        lines = f.readlines()
        for line in lines:
            parts = line.split(" ")
            latitude = parts[0]
            longitude = parts[1].replace("\n", "")
            out.write('<point id="{0}"><title>Point {0}</title><description>Point {0}</description><category id="0">Category</category><condition>Open</condition><latitude>{1}</latitude><longitude>{2}</longitude>'.format(i, latitude, longitude))
            if not i == (len(lines) - 1):
                out.write('<connections><connection>{0}</connection></connections>'.format(i+1))
            out.write('</point>\n')
            i = i + 1
    out.write('<misc></misc></data>\n')