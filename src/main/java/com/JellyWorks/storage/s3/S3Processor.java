package com.JellyWorks.storage.s3;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class S3Processor {

  private final String bucketName = "aws-website-ramyaravishankar-m8";
  //private final String file = "RawInput.txt";

  @Autowired
  S3Invoker s3invoker;
  
  public String readFromS3(String fileName) throws IOException {
	  if(s3invoker.invoke(bucketName, fileName))
		  return fileName+" has been processed.";
	  else
		  return fileName+" not processed.";
  }
}
