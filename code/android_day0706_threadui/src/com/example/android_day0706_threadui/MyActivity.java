package com.example.android_day0706_threadui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity implements Runnable {
	private TextView textViewTime;
	private Thread thread;
	private ProgressBar progressBar;

	//update UI by Handler, subThread can send message to UI thread by Handler
	private Handler handler;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		textViewTime = (TextView) this.findViewById(R.id.text_time);
		progressBar = (ProgressBar) this.findViewById(R.id.progressbar);

		handler = new Handler() {

			//the method run in the Thread that current Handler belong to
			//handle message in fact, when Handler received message ,the method
			//will called automatically by current Thread
			//param :send by other thread
			//the class Message include many public member that use to pass information
			//you can define by yourself
			public void handleMessage(Message msg) {
				if (msg != null) {

					//what: the type of message
					int what = msg.what;
					int currentTime = msg.arg1;
					int max = msg.arg2;
					switch (what) {
						case 888:
							String str = (String) msg.obj;
							textViewTime.setText(str);
							progressBar.setMax(max);
							progressBar.setProgress(currentTime);
							break;
					}
				}
			}
		};
	}

	//display time
	public void btnStart_onclick(View view) {
		if (thread == null || !thread.isAlive()) {
			this.thread = new Thread(this);
			thread.start();
		} else {
			thread.start();
		}
	}

	public void btnRandom_onclick(View view) {
		Toast.makeText(this, "click me !", Toast.LENGTH_LONG).show();
	}

	public void run() {
		long time = 0;
		while (true) {
			String s = Long.toString(time);

			//create Message that you want to sent to UI Thread
			Message message = new Message();
			message.what = 888;
			message.obj = s;
			message.arg1 = (int) time;
			message.arg2 = 10000;

			//send message to UI thread by Handler
			handler.sendMessage(message);
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			time += 100;
			if (time > 10000) {
				break;
			}
		}
	}
}
