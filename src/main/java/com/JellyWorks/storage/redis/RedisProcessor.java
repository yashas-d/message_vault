package com.JellyWorks.storage.redis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.JellyWorks.entity.ResponseObject;

@Component
public class RedisProcessor {

  @Autowired
  RedisInvoker invoker;
  
  public ResponseObject getMessages(String date) {
    ResponseObject entity = new ResponseObject();
    entity.setTexts((List<String>) invoker.readFromCache(date));
    return entity;
  }
}
