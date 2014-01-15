PigLatin google-trend filter
=============================

A filter for google trend keywords developed in pig latin.

Idea

The increasingly growing number of information around internet have ended in the need of different tools as search engines to simplify the user’s process of finding relevant data.

As the search engines indexes the different sites, some keywords can obtain a different relevance value which implies in a better overall ranking during the searches. On the other side of the problem, the organizations might want to take this into their advantage, using different kind of techniques, known as Search engine optimization (SEO) to improve their visibility.

However, those techniques can also be used by intruders in order to efficiently spread their attacks. This is commonly known as black hat SEO.

A basic approach to attract traffic is the use of the Google Trends keywords in order to automatically create several malware blogs [1] completely stuffed with those terms. Later, the search engine will index those pages and many searches will rank them in a high position. This problem can be considered as “poisoned” search results [2, 3].
The main idea with the current project is that, using Big Data and different subsets of popular keywords, it is possible to identify those sites that are highly likely to be malware pages.

References
[1]	Danchev, D. (2008, Oct. 2). Cybercriminals syndicating Google Trends keywords to serve malware. ZDNet. Retrieved from: http://www.zdnet.com/blog/security/cybercriminals- syndicating-google-trends-keywords-to-serve-malware/1995
[2]	Howard, F., & Komili, O. (2010). Poisoned search results: How hackers have automated search engine poisoning attacks to distribute malware. Sophos Technical Papers.
[3]	Landesman, M. (n.d.). Search Engine Poisoning. About.com. Retrieved from: http://antivirus.about.com/od/securitytips/a/Search-Engine-Poisoning.htm

How to setup:
First download & install Pig Latin -> http://apache.mirror1.spango.com/pig/
Preferable version 0.10.1
After installation download the Pig Latin script (run.pig) together with:
Keyword_Count.java 
Keyword_Ratio.java

As example input google trend files you can download them here as well.
These are:
GoogleTrend_NL.csv
GoogleTrend_US.csv
GoogleTrend_WORLD.csv

Then download the convert.py script.
This is for converting the csv files into a readable format for using it in Pig Latin.

How to Run:
./convert.py '/home/user/GoogleTrend_US.csv' > GT_US.log
./convert.py '/home/user/GoogleTrend_NL.csv' > GT_NL.log
./convert.py '/home/user/GoogleTrend_WORLD.csv' > GT_WORLD.log

mkdir udf_KW
cp Keyword_Count.java ./udf_KW
cp Keyword_Ratio.java ./udf_KW
javac -cp ./pig-0.10.0.jar udf_KW/Keyword_Count.java
javac -cp ./pig-0.10.0.jar udf_KW/Keyword_Ratio.java
jar -cf udf_KW.jar udf_KW
pig -x local run.pig > result

