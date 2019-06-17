package edu.handong.data.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Utils {

public static void writeAFile(ArrayList<ArrayList<String>> ArrayListlines, String targetFileName) {
		//taretFileName�� �ùٸ� ��ο�, �����ϴ� �������� Ȯ���ؾ���.
		//�׸��� lines�� �ִ� �� file�� �Է��ؾ���.
	
		 File file = new File(targetFileName);
		 
		 //File directory = new File(tmp.getParentFile().getAbsolutePath());
		 //directory.mkdirs();
		 
		 if(!(file.getParentFile()==null)&&!file.getParentFile().exists()){
			// System.out.println("dd");
			 file.getParentFile().mkdirs();
		 }
		 
		 FileWriter writer = null;
		 
		 try {
			 writer = new FileWriter(file, false);
			 for(ArrayList<String> lines:ArrayListlines) {
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
