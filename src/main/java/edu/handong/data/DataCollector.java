package edu.handong.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;

import edu.handong.data.utils.ExcelReader;
import edu.handong.data.utils.JuhuiArrayList;
import edu.handong.data.utils.Utils;



public class DataCollector implements Runnable{

	String input;
	String output;
	String outputflie2;
	private JuhuiArrayList<ArrayList<String>> result1;
	private JuhuiArrayList<ArrayList<String>> result2;

	boolean help;
	
	//C:\Users\«—¡÷»Ò\Desktop\hw5data.csv C:\Users\«—¡÷»Ò\Desktop\Iwannagobakchame\ladfasdfasdfsdfd.csv

	public static void main(String[] args) {

		DataCollector DC = new DataCollector();		
		Options options = DC.createOptions();
		
		if(DC.parseOptions(options, args)){


		if (DC.help||DC.output==null){
			DC.printHelp(options);
			return;
		}
		
	
		ArrayList<String> filename=new ArrayList<String>();
		DC.result1 = new JuhuiArrayList<ArrayList<String>>();
		DC.result2 = new JuhuiArrayList<ArrayList<String>>();
		
		File dirFile = new File(DC.input);
		File []fileList=dirFile.listFiles();
		String str = DC.output;
		String[] outputfliname = str.split("\\.");
		
		ZipFile zipFile;
		try {
			

			boolean header=true;
			int filenum=1;
			for(File tempFile: fileList) {
				if(tempFile.isFile()) {
				zipFile = new ZipFile(tempFile);

				Enumeration<? extends ZipArchiveEntry> entries = zipFile.getEntries();
				int order =0;
			    while(entries.hasMoreElements()){

			    	
			    	
			    	ZipArchiveEntry entry = entries.nextElement();
			        InputStream stream = zipFile.getInputStream(entry);
			    
			        ExcelReader myReader = new ExcelReader();
			        
			        String resultFilename= "000"+ Integer.toString(filenum);
			        if(order==0)DC.result1.add(myReader.getData(stream,header,order,resultFilename));
			        else DC.result2.add(myReader.getData(stream,header,order,resultFilename));
			        
			        
			        order++;
			    }
	
			    header=false;
			    zipFile.close();
			}filenum++;
		}
			DC.outputflie2=outputfliname[0]+"2."+outputfliname[1];

			Thread Filemaker = new Thread(DC,"√ππ¯¬∞");
			Filemaker.start();
			
			
			Utils.writeAFile(DC.result1,outputfliname[0]+"1."+outputfliname[1]);
	        
			
			
			
			
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
	
	public void run() {
		Utils.writeAFile(result2,outputflie2);
	}
	

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();


		try {

			CommandLine cmd = parser.parse(options, args);

			input = cmd.getOptionValue("i");
			output = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
			

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


