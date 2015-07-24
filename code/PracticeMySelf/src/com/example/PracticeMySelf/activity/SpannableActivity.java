package com.example.PracticeMySelf.activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.TextView;
import com.example.PracticeMySelf.R;
/**
 * project:com.example.PracticeMySelf.activity
 * user:VennUser
 * date:2015/7/5
 */
public class SpannableActivity extends Activity {
	private TextView textView;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spanable);

		//get TextView you want to set link
		textView = (TextView) this.findViewById(R.id.text_baidu);

		//create SpannableString
		SpannableString spannableString = new SpannableString("百度");

		//create URLSpan you want to link
		URLSpan urlSpan = new URLSpan("http://www.baidu.com");

		//set URLSpan on SpannableString
		spannableString.setSpan(urlSpan, 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

		//set SpannableString on TextView
		textView.setText(spannableString);

		//set LinkMovementMethod for textView
		textView.setMovementMethod(LinkMovementMethod.getInstance());
	}
}