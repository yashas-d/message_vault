package com.JellyWorks.storage.Local;

import com.JellyWorks.Helpers.PreprocessFile;

import java.io.IOException;
import java.io.InputStream;



public class LocalInputInvoker {

	public LocalInputInvoker(){
	}
 
	public boolean invoke(InputStream inputStreamReader) throws IOException{

		PreprocessFile file=new PreprocessFile(inputStreamReader);
		return file.splitFile();
	}
	
}
