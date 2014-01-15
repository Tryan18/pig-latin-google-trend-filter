register /home/participant/git/commoncrawl-examples/lib/*.jar;

register /home/participant/git/commoncrawl-examples/dist/lib/commoncrawl-examples-1.0.1.jar;

register udf_KW.jar;

a = LOAD '/home/participant/data/*.arc.gz' USING org.commoncrawl.pig.ArcLoader() as (date, length:int, type, statuscode, ipaddress, url:chararray, html:chararray);

b = LOAD '/home/participant/pig_scripts/GT_NL.log' as (keyword:chararray);

count = foreach a generate url, html, udf_KW.Keyword_Count(html, b.$0) as total;

results = filter count by total > 8;

results = foreach results generate url, total, udf_KW.Keyword_Ratio(html, total) as ratio;

dump results;
