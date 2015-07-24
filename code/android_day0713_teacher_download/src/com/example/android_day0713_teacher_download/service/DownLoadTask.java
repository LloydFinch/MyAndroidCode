package com.example.android_day0713_teacher_download.service;

/**
 * Created by VennUser on 2015/7/13.
 */
public class DownLoadTask {

	private long current;
	private long max;
	private String url;

	public DownLoadTask() {
	}

	public DownLoadTask(long current, long max, String url) {
		this.current = current;
		this.max = max;
		this.url = url;
	}

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
