package com.JellyWorks.storage.Local;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import com.JellyWorks.storage.Local.LocalInputInvoker;


public class LocalInputProcessor {
	private LocalInputInvoker invoker;
	public LocalInputProcessor(){
		invoker=new LocalInputInvoker();
	}
 
	public String process(String fileName) throws IOException{
		try {
			if(invoker.invoke(fileName))
				return fileName+" has been processed.";
			else
				return fileName+" not processed.";
		}
		catch (IOException e) {
			//e.printStackTrace();
			return "File processing interuppted due to Exception";
		}
		
	}
}
