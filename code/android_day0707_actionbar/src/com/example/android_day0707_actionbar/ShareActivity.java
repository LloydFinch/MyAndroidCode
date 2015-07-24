package com.example.android_day0707_actionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ShareActionProvider;

/**
 * project:com.example.android_day0707_actionbar
 * user:VennUser
 * date:2015/7/7
 */
public class ShareActivity extends Activity implements TextWatcher {

	//分享的按钮提取成成员，为了实时的分享，可以及时更新分享的操作
	private ShareActionProvider actionProvider;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);

		EditText editText = (EditText) this.findViewById(R.id.txt_edit);
		editText.addTextChangedListener(this);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_share, menu);

		//进行ActionProvider的设置
		MenuItem item = menu.findItem(R.id.action_share);
		if (item != null) {
			actionProvider = (ShareActionProvider) item.getActionProvider();

			//设置隐式意图
			Intent intent = new Intent(Intent.ACTION_SEND);

			//设置分享的类型,格式：大类型/小类型
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, "你是250!");

			actionProvider.setShareIntent(intent);
		}

		return true;
	}

	public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	}

	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	public void afterTextChanged(Editable s) {
		String content = s.toString();
		if (content.length() > 0) {
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, content);
			actionProvider.setShareIntent(intent);
		}
	}
}