package com.JellyWorks.storage.mongoDB;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import com.JellyWorks.Entity.Message;


@Component
public interface MessageRepository extends MongoRepository<Message,String> {

	public Message findByDate(String date);

}