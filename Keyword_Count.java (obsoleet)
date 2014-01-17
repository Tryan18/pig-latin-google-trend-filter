package udf_KW;

import java.io.IOException;
import java.util.Arrays;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class Keyword_Count extends EvalFunc<Long> {

	@Override
	public Long exec(Tuple input) throws IOException {
		Long total = 0L;
		if(input != null && !input.isNull()) {
			String _html = (String)input.get(0);
			String _keywords = (String)input.get(1);

			if(_html != null && !_html.isEmpty() && _keywords != null && !_keywords.isEmpty()) {
				int threshold = 8; // Should also be a parameter
				String[] keywords = _keywords.toString().split(",");
				keywords = Arrays.copyOfRange(keywords, 0, Math.min(threshold, keywords.length));
				
				for(String keyword : keywords) {
					int index = _html.indexOf(keyword);
					while (index >= 0) {
					    total++;
					    index = _html.indexOf(keyword, index + 1);
					}
				}
			}
		}

		return total;
	}

}
