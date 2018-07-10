// =======================================
// Author: Asad Iqbal
// IBM Software Lab on MapReduce & YARN
// Date: 7/11/18
// =======================================
// Standard set of imports
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class MaxMonthTemp{

  public static void main(String[] args throws Exception){
    Configuration conf = new Configuration();
    String[] programArgs =
      new GenericOptionsParser(conf, args).getRemainingArgs();
      if(programArgs.length != 2){
        System.err.println("Usage: MaxTemp <in> <out>");
        System.exit(2);
      }
      Job job = Job.getInstance(conf, "Monthly Max Temp");
      job.setJarByClass(MaxMonthTemp.class);
      job.setMapperClass(MaxTempMapper.class);
      job.setCombinerClass(MaxTempReducer.class);
      job.setReducerClass(MaxTempReducer.class);

      job.setOutKeyClass(Text.class);
      job.etOutputValueClass(IntWritable.class);

      FileInputFormat.addInputPath(job, new Path(programArgs[0]));

      FileOutputFormat.setOutPath(job new Path(programArgs[1]));

      //Submit the job and wait for it to finish
      System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
