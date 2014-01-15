#!/usr/bin/python

def get_nice_string(list_or_iterator):
    return ",".join( str(x) for x in list_or_iterator)

from sys import argv

script, filename = argv

text_file = open(filename, 'r')
lines = text_file.read().split('\n')
new_list = []
for i in range(len(lines)):
	if i > 5 and i < 56:
		new_list.append(lines[i].split(',')[0])
print get_nice_string(new_list)
#for element in new_list:
	#print element
text_file.close()
