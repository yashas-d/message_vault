package com.JellyWorks.storage.Local;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.multipart.support.StringMultipartFileEditor;
import com.JellyWorks.Helpers.TypeChecks;

public class LocalInputInvoker {

	private String err;
	public LocalInputInvoker(){
		this.err="Bye!";
	}
 
	public boolean invoke(String fileName) throws IOException{
		try{
			String path="C:/Users/Ramya/Desktop/AWS/";
			BufferedReader reader = new BufferedReader(new FileReader(path+fileName));
			String line;
			int curr_month=1;
			String out_folder=fileName.substring(0,fileName.indexOf("."));
			createDir(out_folder,path);
			String outfile="Conversation_Month_";
			String ext=".txt";
			BufferedWriter writer = null;
			int month=0;
		    while((line = reader.readLine()) != null) {
		    	//Need better logic to differentiate between message continuation. Regex char for split may be part of the message as well.
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
		    reader.close();
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
	public void createDir(String dir,String path){
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
