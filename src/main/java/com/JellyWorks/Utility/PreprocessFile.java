package com.JellyWorks.Utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.JellyWorks.Utility.TypeChecks;
import com.JellyWorks.storage.redis.RedisProcessor;

import org.springframework.cache.annotation.Cacheable;
import java.net.URISyntaxException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class PreprocessFile {

	private String path;
	private InputStream inputStream;
	private ConfigurableApplicationContext configurableApplicationContext;
	
	public PreprocessFile(InputStream i) throws IOException{
		this.inputStream=i;
		this.path="C:/Users/Ramya/Desktop/AWS/";
		this.configurableApplicationContext = new AnnotationConfigApplicationContext(RedisProcessor.class);
	}
	@Cacheable("splitFile")
	public boolean splitFile() throws URISyntaxException, Exception {
		BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
		BufferedWriter writer=null;
		try{
			String line="";
			int curr_month=0;
			String out_folder="Output";
			createDir(out_folder,path);
			String outfile="Conversation_Month_";
			String ext=".txt";
			StringRedisTemplate redisTemplate = (StringRedisTemplate) configurableApplicationContext.getBean("strRedisTemplate");
			ValueOperations<String, String> values = redisTemplate.opsForValue();
			String key="default";
			int count=0;
			int month=0;
		    while((line=reader.readLine()) != null){ 
		    	//Need better logic to differentiate between message continuation. Regex char for split may be part of the message as well.
		    	if(line.indexOf(",")!=-1){
		    		String date_of_msg=line.substring(0, line.indexOf(","));
		    		key=date_of_msg;
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
		    	if(!writer.equals(null)){
		    		//Need better logic for key
		    		writer.write(line);
		    		count++;
		    		key=Integer.toString(count);
		    		if(values.get(key)!=null)
		    			values.set(key, line);
		    		System.out.println(line);
		    		System.out.println("Value added: " + values.get(key) + " for "+ key);
		    		writer.newLine();
		    	}
		    }
		    	return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		finally {
			reader.close();
		    if(writer!=null){
			    writer.close();
		    }
			configurableApplicationContext.close();
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
