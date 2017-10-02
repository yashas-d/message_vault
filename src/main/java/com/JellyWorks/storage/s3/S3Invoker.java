package com.JellyWorks.storage.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectMetadataRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class S3Invoker {

  private final AWSCredentials credentials = 
      new BasicAWSCredentials("PlaceHolder", "PlaceHolder");
  private final AmazonS3 s3client = new AmazonS3Client(credentials);

  public StringBuffer invoke(String bucketName, String file) throws IOException {
    S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, file));
    System.out.println(s3object.getObjectMetadata().getContentType());
    System.out.println(s3object.getObjectMetadata().getContentLength());

    BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
    String line;
    StringBuffer content = new StringBuffer();
    while((line = reader.readLine()) != null) {
      System.out.println(line);
      content.append(line);
    }
    return content;
  }	
}
