package cs3390.parser;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class WikiParser {
    
	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		 	conf.set("xmlinput.start", "<page>");
		    conf.set("xmlinput.end", "</page>");
		    conf.set("keyFile", args[1]);
		  
		for (int i = 0; i < 20; i++ ) {
			
		}
		Job job = new Job(conf);
		conf.set("pageid", args[1] );
		job.setJobName("Wiki Parser Job");
		job.setJarByClass(WikiParser.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setMapperClass(WikipediaMapper.class);
		job.setReducerClass(WikipediaReducer.class);
	    MultipleOutputs.addNamedOutput(job, "pageid", TextOutputFormat.class,	Text.class, Text.class);
        job.setNumReduceTasks(10);
        job.setInputFormatClass(XmlInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class); 
        FileInputFormat.addInputPath(job, new Path(args[0]));  
    		FileOutputFormat.setOutputPath(job, new Path(args[1]));
    		
    		job.waitForCompletion(true);
    		
    		
    	
    		
 }
        
}