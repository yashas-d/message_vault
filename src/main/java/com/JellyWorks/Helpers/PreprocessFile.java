package com.JellyWorks.Helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.JellyWorks.Helpers.TypeChecks;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

public class PreprocessFile {

	private String path;
	private InputStream inputStream;
	public PreprocessFile(InputStream i) throws IOException{
		this.inputStream=i;
		this.path="C:/Users/Ramya/Desktop/AWS/";
	}
	@Cacheable("splitFile")
	public boolean splitFile(){
		try{
			BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
			String line;
			int curr_month=1;
			String out_folder="Output";
			createDir(out_folder,path);
			String outfile="Conversation_Month_";
			String ext=".txt";
			BufferedWriter writer = null;
			int month=0;
		    while((line=reader.readLine()) != null) {
		    	//Need better logic to differentiate between message continuation. Regex char for split may be part of the message as well.
		    	System.out.println(line);
		    	if(line.indexOf(",")!=-1){
		    		String date_of_msg=line.substring(0, line.indexOf(","));
		    		if(TypeChecks.isValidDate(date_of_msg)){
		    			String month_of_msg=date_of_msg.substring(0,date_of_msg.indexOf("/"));
			    		if(TypeChecks.isNumeric(month_of_msg))
			    			month=Integer.parseInt(month_of_msg);
				    	if(month!=curr_month){
				    		if(writer!=null)
				    			writer.close();
				    		curr_month=month;
				    		writer=new BufferedWriter(new FileWriter(path+out_folder+"/"+outfile+curr_month+ext));
				    	}	
		    		}
		    	}
		    	if(writer!=null){
		    		System.out.println(line);
		    		writer.write(line);
		    		writer.newLine();
		    	}
		    	
		    }
		    inputStream.close();
		    if(writer!=null){
			    writer.close();
		    }
		    
		    return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}	
	}
	private void createDir(String dir,String path){
		File theDir = new File(path+"/"+dir);

		if (!theDir.exists()) {
		    System.out.println("creating directory: " + theDir.getName());
		    boolean result = false;

		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(result) {    
		        System.out.println("DIR created");  
		    }
		}
	}
}
