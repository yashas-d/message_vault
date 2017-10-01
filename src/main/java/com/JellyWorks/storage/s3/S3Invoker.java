package com.JellyWorks.storage.s3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.JellyWorks.storage.redis.RedisInvoker;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class S3Invoker {

  @Autowired
  RedisInvoker redisInvoker;
  
  private final AWSCredentials credentials = 
      new BasicAWSCredentials("AKIAI34MMHYGX2WMZTIA", "tycUt1Wd3XKBWDR1YPOgW5Zqf6NHRIgPPtN1Vidb");
  private final AmazonS3 s3client = new AmazonS3Client(credentials);

  public void invoke(String bucketName, String file) throws IOException {
    S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName, file));

    BufferedReader reader = new BufferedReader(new InputStreamReader(s3object.getObjectContent()));
    fileContentsProcessor(reader);
  }	
  
  private void fileContentsProcessor(BufferedReader reader) throws IOException {
    String line, date, txt;
    line = date = txt = "";
    List<String> messages = new ArrayList<>();
    String initDate = "";
    
    while((line = reader.readLine()) != null) {
      
      int commaIndex = line.indexOf(',');
      if(commaIndex > 0 ) {
        date = line.substring(0,commaIndex);
        date = date.replace('/', '-');
        txt = line.substring(commaIndex+2);
      } else {
        txt = line;
      }
      
      if("".equals(initDate) || initDate.equals(date)) {
        messages.add(txt);
        initDate = date;
      } else {
        // Add to cache'
        redisInvoker.writeToCache(initDate, messages);
        // Wipe data
         initDate = date;
         messages.clear();
        // Insert to map
        messages.add(txt);
      }  
   }
  }
}
