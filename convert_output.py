#!/usr/bin/python

from sys import argv
if len(argv) != 3:
        print "Usage:\n ./convert_output.py -d /home/participant/pig_scripts/third_version/group_assignment_1.pig_result \n It will create a new file for filter on domains. \n ./convert_output.py -u /home/participant/pig_scripts/third_version/group_assignment_1.pig_result \n It will create a new file for filtering on urls."
elif argv[1] == "-u":
	text_file = open(argv[2],'r')
	lines = text_file.read().split('\n')
        bf_file = "converted_output_urls"
        big_file = open(bf_file,'w')
        for i in range(len(lines)):
		temp_line = lines[i].split(',')
		temp_line = temp_line[0][1:]
		big_file.write(temp_line+"\n");
        big_file.close();
elif argv[1] == "-d":
	text_file = open(argv[2],'r')
        lines = text_file.read().split('\n')
        bf_file = "converted_output_domains"
        big_file = open(bf_file,'w')
        for i in range(len(lines)):
                temp_line = lines[i].split(',')
                temp_line = temp_line[0][1:]
		if "http://" in temp_line:
			temp_line = temp_line[7:]
		if "www." in temp_line:
			temp_line = temp_line[4:]
		temp_line = temp_line.split('/')[0]
                big_file.write(temp_line+"\n");
        big_file.close();

