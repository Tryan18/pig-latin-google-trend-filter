package udf_KW;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

public class Keyword_Ratio extends EvalFunc<Float> {

	@Override
	public Float exec(Tuple input) throws IOException {
		Float total = 0.0F;
		if(input != null && !input.isNull()) {
			String _html = (String)input.get(0);
			Long _count = (Long)input.get(1);

			if(_html != null && !_html.isEmpty() && _count != null) {
				int words = _html.trim().split("\\s+").length; // Not ok: remove tags and stopwords

				total = BigDecimal.valueOf(_count).divide(BigDecimal.valueOf(words), 4, RoundingMode.HALF_UP).floatValue();
			}
		}
		return total;
	}

}
