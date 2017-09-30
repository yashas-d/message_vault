package com.JellyWorks.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JellyWorks.storage.s3.S3Processor;

@RestController
@RequestMapping("/")
public class Controller {

  @Autowired
  S3Processor s3processor;

  @RequestMapping("/home")
  public String index() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/readFile")
  public String fileReader() throws IOException {
    s3processor.readFromS3();
    return "Done printing to console";
  }
}