#!/usr/bin/python

from sys import argv
if len(argv) != 2:
        print "Usage:\n ./convert_output.py /home/participant/pig_scripts/third_version/group_assignment_1.pig_result \n It will create a new file"
else:
	text_file = open(argv[1],'r')
	lines = text_file.read().split('\n')
        bf_file = "converted_output"
        big_file = open(bf_file,'w')
        for i in range(len(lines)):
		temp_line = lines[i].split(',')
		temp_line = temp_line[0][1:]
		big_file.write(temp_line+"\n");
        big_file.close();

