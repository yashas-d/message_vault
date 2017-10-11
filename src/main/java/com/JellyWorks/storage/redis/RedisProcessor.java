package com.JellyWorks.storage.redis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.JellyWorks.Entity.ResponseObject;

@Component
public class RedisProcessor {
	
	@Autowired
	RedisInvoker redisinvoker;
	
	@SuppressWarnings("unchecked")
	public ResponseObject getMessages(String date) {
	    ResponseObject entity = new ResponseObject();
	    entity.setTexts((List<String>) redisinvoker.readFromCache(date));
	    return entity;
	  }
	
}


