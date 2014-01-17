//UDF for Pig Latin for counting google trend keywords inside html source code

package udf_KW;

import java.io.IOException;
import java.util.Arrays;
import java.lang.Integer;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.apache.pig.data.DataType;

/*
A  = LOAD 'data' AS (f1:chararray);

DUMP A;
(Here is the first string.)
(Here is the second string.)
(Here is the third string.)

X = FOREACH A GENERATE TOKENIZE(f1);

DUMP X;
({(Here),(is),(the),(first),(string.)})
({(Here),(is),(the),(second),(string.)})
({(Here),(is),(the),(third),(string.)})
*/


public class FindKeywords extends EvalFunc<DataBag> {
    TupleFactory mTupleFactory = TupleFactory.getInstance();
    BagFactory mBagFactory = BagFactory.getInstance();
    @Override
    public DataBag exec(Tuple input) throws IOException 
    {
            DataBag output = mBagFactory.newDefaultBag();
            Float total = 0.0F;
                if(input != null && !input.isNull())
                {
                        String _html = (String)input.get(0);
                        String _keywords = (String)input.get(1);

                        if(_html != null && !_html.isEmpty() && _keywords != null && !_keywords.isEmpty())
                        {
                                //Count keywords
                                int threshold = 8; // Should also be a parameter
                                String[] keywords = _keywords.toString().split(",");
                                keywords = Arrays.copyOfRange(keywords, 0, Math.min(threshold, keywords.length));
				Tuple[] tuples = new Tuple[keywords.length];
				if(keywords.length != 0)
				{
				for(int i=0;i<keywords.length;i++)
                                {
					tuples[i] = mTupleFactory.newTuple(2);
					tuples[i].set(0,keywords[i]);
					tuples[i].set(1,0);
                                }
				}
                                String[] html_words = _html.split("\\s+");
                                for(String hw : html_words)
                                {
                                        for(int i=0;i<tuples.length;i++)
                                        {
                                                if(hw.equals(tuples[i].get(0)))
                                                {
							int counter = Integer.valueOf(tuples[i].get(1).toString());
							counter++;
							tuples[i].set(1,counter);
						}
                                        }
                                }
				for(Tuple t : tuples)
		                {
                		        output.add(t);
		                }

			}
		}
            return output;
        }
    }
/*
    public Schema outputSchema(Schema input) {
         try{
             Schema bagSchema = new Schema();
             bagSchema.add(new Schema.FieldSchema("token", DataType.CHARARRAY));

             return new Schema(new Schema.FieldSchema(getSchemaName(this.getClass().getName().toLowerCase(), input),
                                                    bagSchema, DataType.BAG));
         }catch (Exception e){
            return null;
         }
    }*/
