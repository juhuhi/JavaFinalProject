package edu.handong.data.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import edu.handong.data.utils.JuhuiArrayList;

public class Utils {

public static void writeAFile(JuhuiArrayList<ArrayList<String>> ArrayListlines, String targetFileName) {

	
	
	 FileWriter writer = null;
		 
		 try {

			File file = new File(targetFileName);
			 
			 //File directory = new File(tmp.getParentFile().getAbsolutePath());
			 //directory.mkdirs();
			 
			 //if(!(file.getParentFile()==null)&&!file.getParentFile().exists()){
			 if(!file.exists()) {
				// System.out.println("dd");
				 file.getParentFile().mkdirs();
			 }
			 
			 writer = new FileWriter(file, false);
			 for(ArrayList<String> lines: ArrayListlines.get()) {
				 for(String line:lines) {			 
					 writer.write(line);
					 writer.write("\n");
					 writer.flush();
				 }
			 }
			 //System.out.println("done");
			
		} catch(IOException e) {
            e.printStackTrace();
            
        } try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }


}
