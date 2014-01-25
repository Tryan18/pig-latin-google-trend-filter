#!/usr/bin/python
import os

def create_big_text(filename,writefile):
	with open(writefile, "a") as myfile:
		text_file = open(filename, 'r')
		lines = text_file.read().split('\n')
		for i in range(len(lines)):
			if i != (len(lines)-1):
				myfile.write(lines[i]+"\n");
			else:
				myfile.write(lines[i])
		text_file.close()
		myfile.close()

from sys import argv
if len(argv) != 3:
	print "Usage:\n For converting domains ->  ./convert_blacklist.py -d /home/participant/data/blacklists/ \n For converting urls -> ./convert_blacklist.py -u /home/participant/data/blacklists/"
elif argv[1] == "-d":
	bf_file = "big_file_domains"
	big_file = open(bf_file,'w');
	big_file.write("Domains: \n");
	big_file.close();
# traverse root directory, and list directories as dirs and files as files
	for root, dirs, files in os.walk(argv[2]):
		for file in files:
			if file != "CATEGORIES" and file != "urls":
				currentfile = root+"/"+file
				create_big_text(currentfile,bf_file)
elif argv[1] == "-u":
	bf_file = "big_file_urls"
	big_file = open(bf_file,'w');
	big_file.write("Urls: \n");
	big_file.close();
	for root, dirs, files in os.walk(argv[2]):
                for file in files:
                        if file != "CATEGORIES" and file != "domains":
				currentfile = root+"/"+file
				create_big_text(currentfile,bf_file)
