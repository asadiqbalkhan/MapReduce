// =======================================
// Author: Asad Iqbal
// IBM Software Lab on MapReduce & YARN
// Date: 7/11/18
// =======================================
// Standard set of imports
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
// You need a public class with name MaxTempMapper
//  For this class --
// -- a. After importing java.io.IOException
// -- b. Extend Mapper<LongWritable, Text, Text, IntWritable>
// -- c. Define a public class called map.
public class MaxTempMapper extends
  Mapper<LongWritable, Text, Text, IntWritable>{
    @Override
    public void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException{
          String line = value.toString();
          String month = line.subString(22,24);
          int avgTemp;
          avgTemp = Integer.parseInt(line.subString(95,98));
          context.write(new Text(month), new IntWritable(avgTemp));
  }
}
