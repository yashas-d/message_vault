package com.JellyWorks.storage.Local;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import com.JellyWorks.storage.Local.LocalInputInvoker;


public class LocalInputProcessor {
	private LocalInputInvoker invoker;
	public LocalInputProcessor(){
		invoker=new LocalInputInvoker();
	}
 
	public String process(String fileName) throws IOException{
		InputStream inputStreamReader=new FileInputStream("C:/Users/Ramya/Desktop/AWS/"+fileName);
		if(invoker.invoke(inputStreamReader))
			return fileName+" has been processed.";
		else
			return fileName+" not processed.";
		
	}
}
