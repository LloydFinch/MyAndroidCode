package com.example.android_day0711_homework.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.android_day0711_homework.R;
import com.example.android_day0711_homework.tool.DownLoad;


/**
 * Created by VennUser on 2015/7/11.
 */
public class ProgressActivity extends Activity implements Runnable {

	private TextView textView;
	private ProgressBar progressBar;
	private Thread thread;
	private float max;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 111) {
				max = msg.arg1;
				progressBar.setMax((int) max);
			} else if (msg.what == 222) {
				textView.setText(String.valueOf((msg.arg1 / max) * 100) + "%");
				progressBar.setProgress(msg.arg1);
			} else if (msg.what == 444) {
				textView.setText((String) msg.obj);
				finish();
			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);

		textView = (TextView) this.findViewById(R.id.percent_progress);
		progressBar = (ProgressBar) this.findViewById(R.id.progress_current);
		progressBar.setMax((int) DownLoad.getTotal());
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		int total = (int) DownLoad.getTotal();
		Message messageMAX = new Message();
		messageMAX.what = 111;
		messageMAX.arg1 = total;
		handler.sendMessage(messageMAX);
		Log.d("------>", "MAX" + messageMAX.arg1);
		while (progressBar.getProgress() != progressBar.getMax()) {
			Message message = new Message();
			message.what = 222;
			message.arg1 = (int) DownLoad.getProgress();
			Log.d("------>", "CURRENT" + message.arg1);
			handler.sendMessage(message);
			try {
				thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Message message = new Message();
		message.what = 444;
		message.obj = "下载完成";
		handler.sendMessage(message);
	}
}