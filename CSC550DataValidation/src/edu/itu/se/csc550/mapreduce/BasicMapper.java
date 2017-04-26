package edu.itu.se.csc550.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;

/**
 * 
 * @author gaurav-sjsu @Date 26-04-2016
 *
 */
public class BasicMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
	LongWritable in = new LongWritable();
    
    @Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
    	String line = value.toString();
    	if (line.toString().length() > 0) {
			String arrDataAttributes[] = line.toString().split(" ");
			//System.out.println("Value >> "+value);
			value = new Text(arrDataAttributes[0].toString().trim());
			context.write( key, value );
	
    	}
    }

}