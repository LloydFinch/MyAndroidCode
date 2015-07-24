package com.example.Android_Review.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import com.example.Android_Review.R;

/**
 * Created by VennUser on 2015/7/23.
 */
public class HandlerActivity extends Activity {

	private SubThread subThread;

	private Handler uiHandler = new Handler() {
		public void handleMessage(Message msg) {
			Log.d("======>", "uiThread:" + msg.obj);
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_handler);

		subThread = new SubThread();
		subThread.start();
	}

	public void btnSendOnclick(View view) {

		Handler handler = subThread.getHandler();

		Message message = handler.obtainMessage();
		message.obj = "主线程发送";
		handler.sendMessage(message);
	}

	class SubThread extends Thread {

		Handler handler;

		public Handler getHandler() {
			return handler;
		}

		public void run() {
			Looper.prepare();
			handler = new Handler() {
				public void handleMessage(Message msg) {
					Log.d("--------->", "subThread:" + msg.obj);

					Message message = new Message();
					message.obj = "子线程回复";
					uiHandler.sendMessage(message);
				}
			};

			Looper.loop();
		}
	}
}