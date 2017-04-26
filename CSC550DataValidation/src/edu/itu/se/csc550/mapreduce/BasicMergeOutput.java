package edu.itu.se.csc550.mapreduce;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;

public class BasicMergeOutput {
	
	public static boolean mergeReducerOutputFiles(Configuration conf, String sourceDirectory, String destinationDirectory) {
		try 
		{ 
			FileSystem hdfs = FileSystem.get(conf); 
			FileUtil.copyMerge(hdfs, new Path(sourceDirectory), hdfs, new Path(destinationDirectory), false, conf, null); 
		}catch (IOException e) {
			System.out.println("Exception occured.");
			System.out.println("Exiting...");
			return false;
		}
		System.out.println("BasicMergeOutput: Files merged successfully.");
		System.out.println("BasicMergeOutput: Check Dir = "+destinationDirectory);
		return true;
	}
}
