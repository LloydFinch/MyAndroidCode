package com.homework.entity;

/**
 * project:com.homework.entity
 * user:VennUser
 * date:2015/6/23
 */
public class Content
{
	private int imageID;
	private int iconID;
	private String message;

	public Content()
	{
	}

	public Content(int imageID, int iconID, String message)
	{
		this.imageID = imageID;
		this.iconID = iconID;
		this.message = message;
	}

	public int getImageID()
	{
		return imageID;
	}

	public void setImageID(int imageID)
	{
		this.imageID = imageID;
	}

	public int getIconID()
	{
		return iconID;
	}

	public void setIconID(int iconID)
	{
		this.iconID = iconID;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}
}
