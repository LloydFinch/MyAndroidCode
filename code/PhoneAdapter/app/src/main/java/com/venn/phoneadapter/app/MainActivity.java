package com.venn.phoneadapter.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

	private TextView textInch;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textInch = (TextView) this.findViewById(R.id.text_inch);

		//获取手机硬件屏幕信息的操作
		Display display = getWindowManager().getDefaultDisplay();

		//用于获取屏幕尺寸
		DisplayMetrics metrics = new DisplayMetrics();

		display.getMetrics(metrics);

		StringBuilder buider = new StringBuilder();
		buider.append("density:").append(metrics.density);
		buider.append("dpi:").append(metrics.densityDpi);
		buider.append("w:").append(metrics.widthPixels);
		buider.append("h:").append(metrics.heightPixels);
		buider.append("sd:").append(metrics.scaledDensity);
		buider.append("xdpi:").append(metrics.xdpi);
		buider.append("ydpi:").append(metrics.ydpi);


		//scaleDensity = dpi / 160
		Log.d("=========>", buider.toString());
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	private DisplayMetrics getDisplayMetrics() {

		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

		return displayMetrics;
	}
}
