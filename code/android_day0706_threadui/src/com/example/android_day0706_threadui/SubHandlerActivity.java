package com.example.android_day0706_threadui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * project:com.example.android_day0706_threadui
 * user:VennUser
 * date:2015/7/6
 */

//子线程创建Looper于Handler
public class SubHandlerActivity extends Activity {

	private SubThread subThread;
	private EditText editText;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_subhandler);
		editText = (EditText) this.findViewById(R.id.edit_text);
		subThread = new SubThread();
		subThread.start();
	}

	//给子线程发消息
	public void btnSend_onclick(View view) {
		Handler subHandler = subThread.getSubHandler();

		Message message = subHandler.obtainMessage();

		String s = editText.getText().toString();
		message.obj = s;
		subHandler.sendMessage(message);
	}

	public static class SubThread extends Thread {
		private Handler subHandler;

		public Handler getSubHandler() {
			return subHandler;
		}

		public void run() {
			//创建Looper，用于实际接受消息
			//会在当前线程创建一个Looper，该Looper可以通过Looper的myLooper()方法获取
			//若要在线程内部创建Handler必须调用Looper.prepare()方法
			Looper.prepare();

			//创建Handler成员变量，Handler()会自动获取当前线程的Looper()用于消息传递
			subHandler = new Handler() {
				public void handleMessage(Message msg) {
					//TODO 处理其他线程发送的消息
					Log.d("----->", "sub:" + msg.obj);
				}
			};

			//检查当前线程是否存在Looper，包含就死循环等待消息
			Looper.loop();
		}
	}
}