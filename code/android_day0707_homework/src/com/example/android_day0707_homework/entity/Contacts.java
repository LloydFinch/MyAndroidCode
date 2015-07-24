package com.example.android_day0707_homework.entity;

/**
 * project:com.example.android_day0707_homework.entity
 * user:VennUser
 * date:2015/7/7
 */
public class Contacts {
	private String name;
	private String number;

	public Contacts() {
	}

	public Contacts(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
