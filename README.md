PigLatin Google Trend filter
=============================
<br>
A filter for google trend keywords developed in pig latin.
<br>
<br>
<b>Idea</b>
<br>
<br>
The increasingly growing number of information around internet have ended in the need of different tools as search engines to simplify the user’s process of finding relevant data.
<br>
As the search engines indexes the different sites, some keywords can obtain a different relevance value which implies in a better overall ranking during the searches. On the other side of the problem, the organizations might want to take this into their advantage, using different kind of techniques, known as Search engine optimization (SEO) to improve their visibility.
<br>
However, those techniques can also be used by intruders in order to efficiently spread their attacks. This is commonly known as black hat SEO.
<br>
A basic approach to attract traffic is the use of the Google Trends keywords in order to automatically create several malware blogs [1] completely stuffed with those terms. Later, the search engine will index those pages and many searches will rank them in a high position. This problem can be considered as “poisoned” search results [2, 3].
The main idea with the current project is that, using Big Data and different subsets of popular keywords, it is possible to identify those sites that are highly likely to be malware pages.
<br>
<br>
<b>References</b>
<br>
[1]	Danchev, D. (2008, Oct. 2). Cybercriminals syndicating Google Trends keywords to serve malware. ZDNet. Retrieved from: http://www.zdnet.com/blog/security/cybercriminals- syndicating-google-trends-keywords-to-serve-malware/1995
<br>
[2]	Howard, F., & Komili, O. (2010). Poisoned search results: How hackers have automated search engine poisoning attacks to distribute malware. Sophos Technical Papers.
<br>
[3]	Landesman, M. (n.d.). Search Engine Poisoning. About.com. Retrieved from: http://antivirus.about.com/od/securitytips/a/Search-Engine-Poisoning.htm
<br>
<br>
<b>How to setup:</b>
<br>
Please install the VM of Norvig -> http://norvigaward.github.io/vm.html<br>
First download & install Pig Latin -> http://apache.mirror1.spango.com/pig/<br>
Preferable version 0.10.1<br>
After installation download the Pig Latin script (run.pig) together with:<br>
Get_Body.java <br>
FindKeywords.java<br>
<br>
As example input google trend files you can download them here as well.<br>
These are:<br>
GoogleTrend_NL.csv<br>
GoogleTrend_US.csv<br>
GoogleTrend_WORLD.csv<br>
<br>
Then download the convert.py script.<br>
This is for converting the csv files into a readable format for using it in Pig Latin.<br>
<br>
<b>How to Run:</b>
<br>
./convert.py '/home/user/GoogleTrend_US.csv' > GT_US.log<br>
./convert.py '/home/user/GoogleTrend_NL.csv' > GT_NL.log<br>
./convert.py '/home/user/GoogleTrend_WORLD.csv' > GT_WORLD.log<br>
mkdir udf_KW<br>
cp Get_Body.java ./udf_KW<br>
cp FindKeywords.java ./udf_KW<br>
javac -cp ./pig-0.10.0.jar udf_KW/Get_Body.java.java<br>
javac -cp ./pig-0.10.0.jar udf_KW/FindKeywords.java<br>
jar -cf udf_KW.jar udf_KW<br>
pig -x local run.pig > result<br>

