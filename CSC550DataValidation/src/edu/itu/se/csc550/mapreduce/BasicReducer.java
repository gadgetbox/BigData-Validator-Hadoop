package edu.itu.se.csc550.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class BasicReducer extends Reducer<LongWritable, Text, LongWritable, Text> {

	public static Long oldKey = -1l;
	
	@Override
	public void reduce(LongWritable key, Iterable<Text> values,
			Context context) throws IOException, InterruptedException {
		
		String result = "true";
		for ( Text value : values) {
			String [] newValue = value.toString().split("\t");
			LongWritable next = new LongWritable(Long.parseLong(newValue[1]));
			if(oldKey <= next.get()){
				oldKey = next.get();
			}else {
				result = "false";
				System.out.println("###################################################");
				System.out.println("File is not sorted correctly. Stopping the Reducer.");
				System.out.println("###################################################");
				System.exit(1);
			}
		}
		System.out.println(" OldKey "+oldKey);
		context.write(key, new Text(result));	
	}
	
}