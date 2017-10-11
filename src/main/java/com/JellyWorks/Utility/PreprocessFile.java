package com.JellyWorks.Utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import com.JellyWorks.Utility.TypeChecks;
import com.JellyWorks.storage.redis.RedisInvoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Component
public class PreprocessFile {

	@Autowired
	RedisInvoker redisInvoker;
	
	public boolean splitFile(InputStream inputStream) throws URISyntaxException, Exception {
		BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
		try{
			String line, date, txt;
		    line = date = txt = "";
		    List<String> messages = new ArrayList<>();
		    String initDate = "";
		    
		    while((line = reader.readLine()) != null) {
		      
		      int commaIndex = line.indexOf(',');
		      if(commaIndex > 0 ) {
		        String dateTemp = line.substring(0,commaIndex);
		        if(TypeChecks.isValidDate(dateTemp)){
		        	date = dateTemp.replace('/', '-');
		        }
		      } 
		      txt = line;
		      
		      if("".equals(initDate) || initDate.equals(date)) {
		        messages.add(txt);
		        initDate = date;
		      } else {
		        // Add to cache'
		        redisInvoker.writeToCache(initDate, messages);
		        // Wipe data
		         initDate = date;
		         messages.clear();
		        // Insert to map
		        messages.add(txt);
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
		}		
	}
}
