register /home/participant/git/commoncrawl-examples/lib/*.jar;

register /home/participant/git/commoncrawl-examples/dist/lib/commoncrawl-examples-1.0.1.jar;

REGISTER /home/participant/pig_scripts/pig-0.10.0.jar;

register udf_CD.jar;

a = LOAD '/home/participant/data/big_file_urls' AS (blurl:chararray);
a = foreach a GENERATE udf_CD.ConvertDomain(blurl),blurl;

b = LOAD '/home/participant/data/*.arc.gz' USING org.commoncrawl.pig.ArcLoader() as (date, length:int, type, statuscode, ipaddress, url:chararray);
b = foreach b GENERATE udf_CD.ConvertDomain(url),url;

X = JOIN a by $0, b by $0;

c = LOAD '/home/participant/data/main_code_result_dataset' AS (main:tuple(url:chararray,databag1:bag{nameless_1:tuple(nameless_2:tuple(kw:chararray,counter:int))},total:int));
c = foreach c GENERATE main.url;

Z = JOIN X by $3, c by $0;
Z = foreach Z GENERATE $1,$3;
dump Z;
