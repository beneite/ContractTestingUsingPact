package com.rahulshettyacademy.component;

import org.springframework.stereotype.Component;
@Component
public class GreetingComponent {
	
private  long id;
private String content;

public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}



	
}
