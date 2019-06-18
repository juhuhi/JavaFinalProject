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
import edu.handong.data.utils.Utils;
import edu.handong.data.utils.JuhuiArrayList;



public class DataCollector {

	String input;
	String output;
	private JuhuiArrayList<ArrayList<String>> result1;
	private JuhuiArrayList<ArrayList<String>> result2;

	boolean help;
	
	//C:\Users\한주희\Desktop\hw5data.csv C:\Users\한주희\Desktop\Iwannagobakchame\ladfasdfasdfsdfd.csv
	
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
		result1 = new JuhuiArrayList<ArrayList<String>>();
		result2 = new JuhuiArrayList<ArrayList<String>>();
		
//		System.out.println(input);
		File dirFile = new File(input);
		File []fileList=dirFile.listFiles();
		String str = output;
		String[] outputfliname = str.split("\\.");
		
		ZipFile zipFile;
		try {
			

			boolean header=true;
			int filenum=1;
			for(File tempFile: fileList) {
				if(tempFile.isFile()) {
				zipFile = new ZipFile(tempFile);
//				System.out.println("ffffff");
				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				int order =0;
			    while(entries.hasMoreElements()){
//			    	System.out.println(tempFile+": "+order+"번째 파일 시작");
//			    	System.out.println("   ");
//			    	System.out.println("   ");
//			    	System.out.println("   ");
			    	
			    	
			    	ZipArchiveEntry entry = entries.nextElement();
			        InputStream stream = zipFile.getInputStream(entry);
			    
			        ExcelReader myReader = new ExcelReader();
			        
			        String resultFilename= "000"+ Integer.toString(filenum);
			        if(order==0)result1.add(myReader.getData(stream,header,order,resultFilename));
			        else result2.add(myReader.getData(stream,header,order,resultFilename));
			        
			        
			        order++;
			    }
//			    System.out.println(tempFile+": "+"폴더 끝");
//			    System.out.println("   ");
//			    System.out.println("   ");
//			    System.out.println("   ");
			    header=false;
			    zipFile.close();
			}filenum++;
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

		


}
	}
	

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();


		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			
//			System.out.println("input in options : "+input);
//			System.out.println("output in options : "+output);


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


