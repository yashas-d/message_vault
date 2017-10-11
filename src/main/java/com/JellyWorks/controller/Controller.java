package com.JellyWorks.controller;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.JellyWorks.storage.Local.LocalInputInvoker;
import com.JellyWorks.storage.Local.LocalInputProcessor;
import com.JellyWorks.storage.redis.RedisProcessor;
import com.JellyWorks.storage.s3.S3Processor;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.StringIdGenerator;

@Component
@RestController
@RequestMapping("/")
public class Controller {

	LocalInputProcessor localProcessing;
	@Autowired
	S3Processor s3processor;
	
	@Autowired
	RedisProcessor redisprocessor;
	
	@RequestMapping("/home")
	public String index() {
		return "Greetings from Spring Boot!";
	}
	
	@RequestMapping("/readFile/{file}")
	public String fileReader(@PathVariable("file") String fileName) throws Exception {
		return s3processor.readFromS3(fileName+".txt");
	}
	
	//@RequestMapping("/getChat/{daterange}")
	//public ResponseEntity<Object> getChat(@PathVariable("daterange") String daterange) throws Exception{
		
		//return new ResponseEntity<>(redisprocessor.getMessages(daterange),HttpStatus.OK);
	//}
	
	
}