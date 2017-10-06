package com.JellyWorks.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.JellyWorks.storage.Local.LocalInputInvoker;
import com.JellyWorks.storage.Local.LocalInputProcessor;
import com.JellyWorks.storage.s3.S3Processor;

@Component
@RestController
@RequestMapping("/")
public class Controller {

	LocalInputProcessor localProcessing;
	@Autowired
	S3Processor s3processor;
	
	@RequestMapping("/home")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping(value="/readFile", method=RequestMethod.GET, params="file")
	public String fileReader(@RequestParam(value = "file") String fileName) throws IOException {
		/*localProcessing=new LocalInputProcessor();
		try {
			return localProcessing.process(fileName);
		} catch (Exception e) {
			e.printStackTrace();
			return "File not processed";
		}*/
		return s3processor.readFromS3(fileName);
	}
}