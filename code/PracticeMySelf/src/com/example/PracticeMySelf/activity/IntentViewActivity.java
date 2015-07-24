package com.example.PracticeMySelf.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.example.PracticeMySelf.R;
import org.apache.http.impl.client.EntityEnclosingRequestWrapper;

/**
 * Created by VennUser on 2015/7/14.
 */
public class IntentViewActivity extends Activity {

	private EditText editText;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intentview);

		editText = (EditText) this.findViewById(R.id.txt_uri);
	}

	public void btnJump_onclick(View view) {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("http://www.baidu.com"));
		startActivity(intent);
	}

	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		editText.setText(intent.getData().toString());
	}
}