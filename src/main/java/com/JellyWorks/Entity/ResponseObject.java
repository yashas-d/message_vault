package com.JellyWorks.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseObject implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	  
	  @JsonProperty("messages")
	  List<String> texts;
	 
	  public List<String> getTexts() {
	    return texts;
	  }

	  public void setTexts(List<String> texts) {
	    this.texts = texts;
	  }
}
