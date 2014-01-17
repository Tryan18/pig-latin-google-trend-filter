//Filter for retrieving body html tag + removing inner tags

package udf_KW;

import java.io.IOException;
import java.util.Arrays;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Get_Body extends EvalFunc<String> {

	@Override
	public String exec(Tuple input) throws IOException
	{
		String output = "";
		if(input != null && !input.isNull())
		{
			String _html = (String)input.get(0);
			if(_html != null && !_html.isEmpty())
			{
				output = _html;
				int start_index = _html.indexOf("<body");
				start_index += 5;
				int stop_index = _html.indexOf("</body");
				if(start_index >= 0 && start_index < _html.length() && stop_index >= start_index && stop_index < _html.length())
					output = _html.substring(start_index,stop_index);
				Pattern REMOVE_TAGS = Pattern.compile("<.+?>");
				Matcher m = REMOVE_TAGS.matcher(output);
				output = m.replaceAll("");
				REMOVE_TAGS = Pattern.compile("<!--(.+?)-->");
				m = REMOVE_TAGS.matcher(output);
				output = m.replaceAll("");
/*				boolean intag = false;
				String inp = output;
				String outp = "";

				for (int i=0; i < inp.length(); ++i)
				{
				    if (!intag && inp.charAt(i) == '<')
				    {
				        intag = true;
				        continue;
				    }
				    if (intag && inp.charAt(i) == '>')
				    {
				        intag = false;
				        continue;
				    }
				    if (!intag)
				    {
				        outp = outp + inp.charAt(i);
				    }
				}
				output = outp;
//				output = output.replaceAll("\\s+","");
*/
			}
		}
		return output;
	}
}
