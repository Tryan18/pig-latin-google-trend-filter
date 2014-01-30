package udf_CD;

import java.io.IOException;
import java.util.Arrays;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class ConvertURL extends EvalFunc<String>
{

        @Override
        public String exec(Tuple input) throws IOException
        {
                String url = "";
                if(input != null && !input.isNull())
                {
                        String _url = (String)input.get(0);
			String temp = _url;
			if(_url.indexOf("http://") != -1)
			{
				temp = _url.substring(_url.indexOf("http://")+7);
			}
			if(temp.indexOf("www.") != -1)
			{
				temp = temp.substring(temp.indexOf("www.")+4);
			}
			//if(temp.contains("/"))
			//{
			//	temp = temp.split("/")[0];
			//}
			url = temp;
		}
		return url;
	}
}
