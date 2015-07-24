package com.venn.myapplication4.app;

/**
 * Created by VennUser on 2015/7/18.
 */
public class ChatMessage {
	private String from;
	private String to;
	private String content;
	private long time;

	//是否是自己发送
	private boolean self;

	public ChatMessage() {
	}

	public ChatMessage(String from, String to, String content, long time, boolean self) {
		this.from = from;
		this.to = to;
		this.content = content;
		this.time = time;
		this.self = self;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public boolean isSelf() {
		return self;
	}

	public void setSelf(boolean self) {
		this.self = self;
	}
}
