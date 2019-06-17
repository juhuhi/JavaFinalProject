package edu.handong.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.handong.data.utils.ExcelReader;
import edu.handong.data.utils.NotEnoughArgumentException;
import edu.handong.data.utils.Utils;


public class DataCollector {

	String input;
	String output;
	private ArrayList<ArrayList<String>> result1;
	private ArrayList<ArrayList<String>> result2;

	boolean help;
	
	//C:\Users\������\Desktop\hw5data.csv C:\Users\������\Desktop\Iwannagobakchame\ladfasdfasdfsdfd.csv
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
//		try {
//			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
//			if(args.length<2)
//				throw new NotEnoughArgumentException();
//		} catch (NotEnoughArgumentException e) {
//			System.out.println(e.getMessage());
//			System.exit(0);
//		}
		
		Options options = createOptions();
		
		if(parseOptions(options, args)){
		

		if (help||output==null){
			printHelp(options);
			return;
		}
		
	
		ArrayList<String> filename=new ArrayList<String>();
		result1 = new ArrayList<ArrayList<String>>();
		result2 = new ArrayList<ArrayList<String>>();
		
		System.out.println(input);
		File dirFile = new File(input);
		File []fileList=dirFile.listFiles();
		String str = output;
		String[] outputfliname = str.split("\\.");


		System.out.println("dddd"+outputfliname[0]);
		
		ZipFile zipFile;
		try {
			

			boolean header=true;
			for(File tempFile: fileList) {
				if(tempFile.isFile()) {
				zipFile = new ZipFile(tempFile);
				System.out.println("ffffff");
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				int order =0;
			    while(entries.hasMoreElements()){
			    	System.out.println(tempFile+": "+order+"��° ���� ����");
			    	System.out.println("   ");
			    	System.out.println("   ");
			    	System.out.println("   ");
			    	
			    	
			    	ZipArchiveEntry entry = entries.nextElement();
			        InputStream stream = zipFile.getInputStream(entry);
			    
			        ExcelReader myReader = new ExcelReader();
			        
			        
			        if(order==0)result1.add(myReader.getData(stream,header,order));
			        else result2.add(myReader.getData(stream,header,order));
			        order++;
			    }System.out.println(tempFile+": "+"���� ��");
			    System.out.println("   ");
			    System.out.println("   ");
			    System.out.println("   ");
			    header=false;
				
			}
		}
	
			Utils.writeAFile(result1,outputfliname[0]+"1."+outputfliname[1]);
	        Utils.writeAFile(result2,outputfliname[0]+"2."+outputfliname[1]);
			
			
			
			
		} catch 
		(FileNotFoundException e) {
			System.out.println ("The file path does not exist. Please check your CLI argument!");
			System.exit (0);
		} catch (IOException e) {
			System.out.println ("IOException");
			System.exit (0);
		}

		

		
//		if(parseOptions(options, args)){
//			if (help||output==null){
//				printHelp(options);
//				return;
//			}
//			
//		
//			ArrayList<String> filename=new ArrayList<String>();
//
//			File dirFile = new File(input);
//			File []fileList=dirFile.listFiles();
//			for(File tempFile: fileList) {
//				if(tempFile.isFile()) {
//					System.out.println(tempFile.getName());
//					filename.add(tempFile.getName());
//				}
//			}
//			
//			ZipFile zipFile;
//			try {
//				boolean header=false;
//				System.out.println("dd");
//				for(String zipfilename : filename) {
//					System.out.println("sssss");
//					zipFile = new ZipFile(input+zipfilename);
//					System.out.println("ffffff");
//					Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
//					int order =1;
//				    while(entries.hasMoreElements()){
//				    	System.out.println(zipfilename+": "+order+"��° ���� ����");
//				    	
//				    	ZipArchiveEntry entry = entries.nextElement();
//				        InputStream stream = zipFile.getInputStream(entry);
//				    
//				        ExcelReader myReader = new ExcelReader();
//				        if(order==1)Utils.writeAFile(myReader.getData(stream,order),output+"1",header);
//				        else Utils.writeAFile(myReader.getData(stream,order),output+"2",header);
//				        order++;
//				        header=true;
//				    }System.out.println(zipfilename+": "+"���� ��");
//					
//				}
//						
//
//			} catch (FileNotFoundException e) {
//				System.out.println ("The file path does not exist. Please check your CLI argument!");
//				System.exit (0);
//			} catch (IOException e) {
//				System.out.println ("IOException");
//				System.exit (0);
//			}
//
//			
//	}

}
	}
	

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();


		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			
			System.out.println("input in options : "+input);
			System.out.println("output in options : "+output);


		} catch (Exception e) {
			printHelp(options);
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("input")
				.desc("Set an input flie path")
				.hasArg()
				.argName("input path")
				.required()
				.build());
		
		options.addOption(Option.builder("o").longOpt("output")
				.desc("Set an output flie path")
				.hasArg()
				.argName("output path")
				.required()
				.build());
		

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Show a Help page")
		        .argName("Help")
		        .build());
		


		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "JavaFinalProject";
		String footer =";";
		formatter.printHelp("JavaFinalProject", header, options, footer, true);
	}

}


