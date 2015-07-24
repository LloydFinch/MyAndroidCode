package com.example.android_day0707_homework.entity;

import java.util.Date;

/**
 * project:com.example.android_day0707_homework.entity
 * user:VennUser
 * date:2015/7/7
 */
public class Message {
	private String sender;
	private String number;
	private String content;
	private Date date;

	public Message() {
	}

	public Message(String content, Date date, String number, String sender) {
		this.content = content;
		this.date = date;
		this.number = number;
		this.sender = sender;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
