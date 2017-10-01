package com.JellyWorks.storage.s3;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class S3Processor {

  private final String bucketName = "message-vault-storage-vat";
  private final String file = "raw/whatsapp.txt";

  @Autowired
  S3Invoker s3invoker;

  public String readFromS3() throws IOException {
    StringBuffer buffer = s3invoker.invoke(bucketName, file);
    return buffer.toString();
  }
}
