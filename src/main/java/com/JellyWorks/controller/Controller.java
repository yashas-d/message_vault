package com.JellyWorks.controller;


import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JellyWorks.Entity.Message;
import com.JellyWorks.storage.Local.LocalInputProcessor;
import com.JellyWorks.storage.mongoDB.MessageRepository;
import com.JellyWorks.storage.redis.RedisProcessor;
import com.JellyWorks.storage.s3.S3Processor;


@Component
@RestController
@RequestMapping("/")
public class Controller {

	LocalInputProcessor localProcessing;
	@Autowired
	MessageRepository repository;
	
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
	
	@RequestMapping("/getChat/{daterange}")
	public ResponseEntity<Object> getChat(@PathVariable("daterange") String daterange) throws Exception{
		
		return new ResponseEntity<>(repository.findByDate(daterange),HttpStatus.OK);
		//return new ResponseEntity<>(redisprocessor.getMessages(daterange),HttpStatus.OK);
	}
	
	@RequestMapping("/fetchAll")
	public String fetchFromDB() {
		StringBuilder s=new StringBuilder();
		System.out.println("Messages Stored in DB:");
		System.out.println("-------------------------------");
		for (Message message : repository.findAll()) {
			s.append(String.join(", ", message.text));
			s.append("\n");
		}
		return s.toString();
	}
	
	
}