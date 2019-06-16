package edu.handong.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import edu.handong.data.utils.NotEnoughArgumentException;


public class DataCollector {

	String input;
	String output;

	boolean help;
	
	//C:\Users\«—¡÷»Ò\Desktop\hw5data.csv C:\Users\«—¡÷»Ò\Desktop\Iwannagobakchame\ladfasdfasdfsdfd.csv
	
	/**
	 * This method runs our analysis logic to save the number courses taken by each student per semester in a result file.
	 * Run method must not be changed!!
	 * @param args
	 */
	public void run(String[] args) {
		
		try {
			// when there are not enough arguments from CLI, it throws the NotEnoughArgmentException which must be defined by you.
			if(args.length<2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help||output==null){
				printHelp(options);
				return;
			}

			ArrayList<String[]> line= new ArrayList<String[]>();
			try {
				
							

			} catch (FileNotFoundException e) {
				System.out.println ("The file path does not exist. Please check your CLI argument!");
				System.exit (0);
			} catch (IOException e) {
				System.out.println ("IOException");
				System.exit (0);
			}
			

	
			
			if(analysis.equals("1")) {

				ArrayList<String> linesToBeSaved1 = countNumberOfCoursesTakenInEachSemester(sortedStudents);
				Utils.writeAFile(linesToBeSaved1, output);
				
				
			}
			// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
			if(analysis.equals("2")) {
				if(coursecode==null){
					printHelp(options);
					return;					
				}

				Map<String, Coursedate> sortedCourses = new TreeMap<String,Coursedate>(courses);
				ArrayList<String> linesToBeSaved2 = countNumberOfExactCourses(sortedCourses,sortedStudents);
				Utils.writeAFile(linesToBeSaved2, output);
			}
		 
			
			
			
			
			
		
	
//		String dataPath = args[0]; // csv file to be analyzed
//		String resultPath = args[1]; // the file path where the results are saved.
//		ArrayList<String> lines = Utils.getLines(dataPath, true);
//		//System.out.println(lines);
//		
//		students = loadStudentCourseRecords(lines);
//		
//		// To sort HashMap entries by key values so that we can save the results by student ids in ascending order.
//		Map<String, Student> sortedStudents = new TreeMap<String,Student>(students); 
//		
//		// Generate result lines to be saved.
//		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);
//		
//		// Write a file (named like the value of resultPath) with linesTobeSaved.
//		Utils.writeAFile(linesToBeSaved, resultPath);
			
	}

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
		String header = "HGU Course program";
		String footer =";";
		formatter.printHelp("HGUCourseCounter", header, options, footer, true);
	}

}

}
