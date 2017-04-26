package edu.itu.se.csc550.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class GigaByteSortValidatorExecutor extends Configured implements Tool {

	  @Override
		public int run(String[] args) throws Exception {

			if (args.length != 2) {
				System.out
						.printf("Two parameters are required for GigaByteSortValidatorExecutor- <Sorted file input dir> <output dir>\n");
				return -1;
			}

			Job job = new Job(getConf());
			job.setJobName("GigaByteSortValidatorExecutor");
			
			System.out.println(" Executing with Config: ");
			System.out.println(" No of Reducers :  "+job.getNumReduceTasks());
	
			//System.out.println(">> "+getClass());
			job.setJarByClass(getClass());
			
			FileInputFormat.setInputPaths(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job, new Path(args[1]));				
				
			job.setMapperClass(BasicMapper.class);
			job.setMapOutputKeyClass(LongWritable.class);
			job.setMapOutputValueClass(Text.class);

			job.setReducerClass(BasicReducer.class);
			job.setOutputKeyClass(LongWritable.class);
			job.setOutputValueClass(Text.class);
			
			boolean success = job.waitForCompletion(true);
			return success ? 0 : 1;
		}

		
	  public static void main(String[] args) throws Exception {			  

		  System.out.println("... Executing GigaByteSortValidatorExecutor ...");
		  long start = System.currentTimeMillis();	
			int exitCode = ToolRunner.run(new Configuration(),
					new GigaByteSortValidatorExecutor(), args);
			long end = System.currentTimeMillis();
			System.out.println("... GigaByteSortValidatorExecutor Completed ...");
			System.out.println("Total time taken is "+((end-start)/1000)+" Seconds. ");


			System.exit(exitCode);
		}		
	
}
