package com.example.android_day0708_homework.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * project:com.example.android_day0626_homework.MyView
 * user:VennUser
 * date:2015/6/27
 */
public class MarqueeTextView extends TextView
{


	public MarqueeTextView(Context context)
	{
		super(context);
	}

	public MarqueeTextView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	public MarqueeTextView(Context context, AttributeSet attrs,
	                       int defStyle)
	{
		super(context, attrs, defStyle);
	}

	public boolean isFocused()
	{
		return true;
	}

	public boolean didTouchFocusSelect()
	{
		return true;
	}
}
