package com.example.android_day0630_homework.Entity;

/**
 * project:com.example.android_day0630_homework.Entity
 * user:VennUser
 * date:2015/6/30
 */
public class Account {
	private int id;
	private long time;
	private float money;
	private int type;
	private String category;

	public Account() {
	}

	public Account(int id, long time, float money, int type, String category) {
		this.category = category;
		this.id = id;
		this.money = money;
		this.time = time;
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
