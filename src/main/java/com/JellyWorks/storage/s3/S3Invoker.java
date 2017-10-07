package com.JellyWorks.storage.s3;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;
import com.JellyWorks.Utility.PreprocessFile;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

@Component
public class S3Invoker {

	private final AmazonS3 s3client;
	private ProfileCredentialsProvider profile;
  
	public S3Invoker(){
		this.profile=new ProfileCredentialsProvider();
		this.s3client = new AmazonS3Client(this.profile.getCredentials());
		
	}
	public boolean invoke(String bucketName, String fileName) throws URISyntaxException, Exception {
    
		S3Object s3object = s3client.getObject(new GetObjectRequest(bucketName,fileName));      
		InputStream input=s3object.getObjectContent();
		PreprocessFile file=new PreprocessFile(input);
		return file.splitFile();
	  }	
}
