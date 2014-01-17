
register /home/participant/git/commoncrawl-examples/lib/*.jar;

register /home/participant/git/commoncrawl-examples/dist/lib/commoncrawl-examples-1.0.1.jar;

REGISTER /home/participant/pig_scripts/pig-0.10.0.jar;

register udf_KW.jar;

a = LOAD '/home/participant/data/*.arc.gz' USING org.commoncrawl.pig.ArcLoader() as (date, length:int, type, statuscode, ipaddress, url:chararray, html:chararray);

b = LOAD '/home/participant/pig_scripts/GT_NL.log' as (keyword:chararray);

a = filter a by type matches 'text/html';

--a = filter a by url matches '.+nl';

html2 = foreach a generate url,type, udf_KW.Get_Body(html) as short_html;

results = foreach html2 generate url, udf_KW.FindKeywords(short_html,b.$0) as databag1:bytearray;

--results = filter results by kw == 'facebook' AND counter > 0;

c = foreach results GENERATE url,FLATTEN((bag{tuple(chararray,int)})databag1) as (kw:chararray,counter:int);

d = group c by url;

e = foreach d GENERATE group, c.kw, MAX(c.counter);

--results = foreach results GENERATE group, 

--results = foreach results GENERATE url,kw,counter;

e = order e by $2 Desc;

--results = foreach results generate url, ratio,short_html;

--STORE results INTO 'myoutput' USING PigStorage ('*');

f = limit e 30;

dump f;

--r = limit r 10;

--dump r;

--b = LOAD '/home/participant/pig_scripts/GT_NL.log' as (keyword:chararray);

--r = foreach a GENERATE url, REGEX_EXTRACT(html, '/<body>(.*)<\\/body>/s',0) AS body;

--count = foreach a generate url, html, udf_KW.Keyword_Count(html, b.$0) as total;

--results = filter count by total > 8;

--results = foreach results generate url, total, udf_KW.Keyword_Ratio(html, total) as ratio, html;

--results = filter results by ratio > 9;

--results = filter results by url == 'http://www.cfa.org.cn/info/2006/4466.shtml';
--results = order results by ratio Desc;

--results = limit results 10;

--STORE results INTO 'output/query_results_one' USING org.apache.pig.piggybank.storage.MultiStorage('output/query_results_one', '0','none',',');

--dump results;
--STORE results INTO 'myoutput' USING PigStorage ('*');
