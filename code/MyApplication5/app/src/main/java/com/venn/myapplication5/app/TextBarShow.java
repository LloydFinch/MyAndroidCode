package com.venn.myapplication5.app;

import android.widget.TextView;

/**
 * Created by VennUser on 2015/7/18.
 */
public class TextBarShow {


	private String[] strings;
	private TextView textView;


	public TextBarShow(String[] strings, TextView textView) {
		this.strings = strings;
		this.textView = textView;
	}

	//param:index传过来的参数
	public void setStringIndex(int index) {
		textView.setText(strings[index]);
	}
}
