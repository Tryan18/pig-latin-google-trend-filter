register /home/participant/git/commoncrawl-examples/lib/*.jar;

register /home/participant/git/commoncrawl-examples/dist/lib/commoncrawl-examples-1.0.1.jar;

REGISTER /home/participant/pig_scripts/pig-0.10.0.jar;

register udf_KW.jar;

a = LOAD '/home/participant/data/big_file_domains' AS (blurl:chararray);

b = LOAD '/home/participant/pig_scripts/third version/converted_output_domains' AS (url:chararray);

--b = foreach b GENERATE SUBSTRING($0,INDEXOF($0,'www.',0),SIZE($0));
--dump b;

X = JOIN a by $0, b by $0;
dump X;
