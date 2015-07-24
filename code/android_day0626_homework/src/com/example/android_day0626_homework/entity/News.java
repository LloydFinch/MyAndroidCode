package com.example.android_day0626_homework.entity;

/**
 * project:com.example.android_day0626_homework.entity
 * user:VennUser
 * date:2015/6/26
 */
public class News
{
	private String subject;
	private String summary;
	private String corver;
	private String changed;

	public News()
	{
		super();
	}

	public News(String subject, String summary, String corver, String changed)
	{
		super();
		this.subject = subject;
		this.summary = summary;
		this.corver = corver;
		this.changed = changed;
	}

	public String getSubject()
	{
		return subject;
	}

	public void setSubject(String subject)
	{
		this.subject = subject;
	}

	public String getSummary()
	{
		return summary;
	}

	public void setSummary(String summary)
	{
		this.summary = summary;
	}

	public String getCorver()
	{
		return corver;
	}

	public void setCorver(String corver)
	{
		this.corver = corver;
	}

	public String getChanged()
	{
		return changed;
	}

	public void setChanged(String changed)
	{
		this.changed = changed;
	}

	public String toString()
	{
		return "News [subject=" + subject + ", summary=" + summary + ", corver=" + corver
				+ ", changed=" + changed + "]";
	}

}