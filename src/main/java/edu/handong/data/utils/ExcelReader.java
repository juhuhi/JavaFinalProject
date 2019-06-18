package edu.handong.data.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	public ArrayList<String> getData(InputStream is, boolean header,int order,String Filenum) {
		ArrayList<String> values = new ArrayList<String>();
		
		try (InputStream inp = is) {
		    //InputStream inp = new FileInputStream("workbook.xlsx");
		
		        Workbook wb = WorkbookFactory.create(inp);
		        Sheet sheet = wb.getSheetAt(0);
		        if(header) {
		        	Row row = sheet.getRow(order);
		        	String onerow = new String();
	                if (row != null) {
	                    int  cells = row.getPhysicalNumberOfCells();
	                    for (int columnIndex = 0; columnIndex <= cells; columnIndex++) {
	                        Cell cell = row.getCell(columnIndex);
	                        if(cell != null) {
	                        	
	                        	onerow +="\""+(getStringValue(cell).trim().replace("\n", " ").replaceAll("^\"|\"$", ""))+"\""+",";
	                        } 
	                        else {
	                        	if(onerow.length()<1) {onerow+="\""+" "+"\",";};
	                        };
	                    }
	                    values.add("학생번호,"+onerow);
	                    //System.out.println(onerow);
	                  
	                }
		        	
		        }
		
		        int rows = sheet.getPhysicalNumberOfRows(); // 해당 시트의 행의 개수
	            for (int rowIndex=order+1; rowIndex < rows; rowIndex++) {
	                Row row = sheet.getRow(rowIndex); // 각 행을 읽어온다
	                String onerow = new String();
	                if (row != null) {
	                    int  cells = row.getPhysicalNumberOfCells();
	                    for (int columnIndex = 0; columnIndex <= cells; columnIndex++) {
	                        Cell cell = row.getCell(columnIndex);
	                        if(cell != null) {
	                        	onerow +="\""+(getStringValue(cell).trim().replace("\n", " ").replaceAll("^\"|\"$", ""))+"\""+",";
	                        } 
	                        else {
	                        	if(onerow.length()<1) {onerow+="\""+" "+"\",";};
	                        };
	                    }

	                    if (onerow.length()>12) {
	                    values.add("\""+Filenum+"\""+","+onerow);
	                    }
	                    
//	                    System.out.println(onerow);
//	                    System.out.println(onerow.length());
	                    
	              
	                    
	                }
	            }
		    
		      
		        
		    } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return values;
	}
	
	
	
	
	public static String getStringValue(Cell cell) {
		String rtnValue = "";
		try {
			rtnValue = cell.getStringCellValue();
		} catch(IllegalStateException e) {
			rtnValue = Integer.toString((int)cell.getNumericCellValue());            
		}
    
		return rtnValue;
	}
}

