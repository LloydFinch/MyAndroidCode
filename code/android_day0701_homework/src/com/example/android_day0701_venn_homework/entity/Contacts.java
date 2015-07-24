package com.example.android_day0701_venn_homework.entity;

/**
 * project:com.example.android_day0701_venn_homework.entity
 * user:VennUser
 * date:2015/7/1
 */
public class Contacts {
	private String name;
	private String phoneNumber;

	public Contacts() {
	}

	public Contacts( String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String toString() {
		return "Contacts{" +
				"name='" + name + '\'' +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}
}
