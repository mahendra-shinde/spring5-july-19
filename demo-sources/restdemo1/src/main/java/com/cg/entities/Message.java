package com.cg.entities;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Message {

	public Message() {
	}
	
	private String text;
	private Date date;
	
	public Message(String text) {
		super();
		this.text = text;
		this.date = new Date();
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
