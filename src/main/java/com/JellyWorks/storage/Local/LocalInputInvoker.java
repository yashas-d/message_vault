package com.JellyWorks.storage.Local;

import com.JellyWorks.Utility.PreprocessFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;



public class LocalInputInvoker {

	public LocalInputInvoker(){
	}
 
	public boolean invoke(InputStream inputStreamReader) throws URISyntaxException, Exception{

		PreprocessFile file=new PreprocessFile();
		return file.splitFile(inputStreamReader);
	}
	
}
