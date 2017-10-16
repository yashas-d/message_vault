package com.JellyWorks.Entity;

import java.util.List;

import org.springframework.data.annotation.Id;

public class Message {

	 @Id
	 public String id;

    public List<String> text;
    public  String date;

    public Message() {}

    public Message(String date,List<String> text) {
        this.date=date;
    	this.text = text;
    }

}
