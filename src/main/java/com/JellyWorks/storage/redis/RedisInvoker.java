package com.JellyWorks.storage.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisInvoker {
  
  @Autowired
  RedisTemplate redisTemplate;
  
  public void writeToCache(Object key, Object value) {
    ValueOperations values = redisTemplate.opsForValue();
    values.set(key, value);
  }
  
  public Object readFromCache(Object key) {
    ValueOperations values = redisTemplate.opsForValue();
    return values.get(key);
  }
}
