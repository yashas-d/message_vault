package com.JellyWorks.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.JellyWorks.storage.redis.RedisProcessor;
import com.JellyWorks.storage.s3.S3Processor;

@RestController
@RequestMapping("/")
public class Controller {

  @Autowired
  S3Processor s3processor;
  
  @Autowired
  RedisProcessor redisProcessor;

  @RequestMapping("/home")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/readFile")
  public String fileReader() throws IOException {
    s3processor.readFromS3();
    return "Successfully processed the file!";
  }
  
  
  @RequestMapping("/getDate/{date}")
  public ResponseEntity<Object> messagesOnDate(@PathVariable("date") String date) throws Exception {
    return new ResponseEntity<>(redisProcessor.getMessages(date),
        HttpStatus.OK);
  }
  
}