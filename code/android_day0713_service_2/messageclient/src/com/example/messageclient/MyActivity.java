package com.example.messageclient;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.util.Log;
import android.view.View;

import java.lang.reflect.InvocationHandler;

public class MyActivity extends Activity implements ServiceConnection {
	/**
	 * Called when the activity is first created.
	 */
	//用来给服务发消息
	private Messenger sendMessenger;
	private Messenger replyMessenger;

	//如果希望发送的消息能够有返回的结果，则可以变换移位

	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			Log.d("========>", "回复的消息的what: " + msg.what);
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	protected void onResume() {
		super.onResume();

		//绑定到远程服务,绑定远程服务不知道类名，需要使用隐式意图来绑定
		Intent intent = new Intent("com.service.player.PLAY_SERVICE");
		bindService(intent, this, BIND_AUTO_CREATE);
	}

	public void onServiceConnected(ComponentName name, IBinder service) {

		sendMessenger = new Messenger(service);
		replyMessenger = new Messenger(handler);

		//进行消息的发送
		Message message = new Message();
		message.what = 111;
		message.replyTo = replyMessenger;
		try {
			sendMessenger.send(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void onServiceDisconnected(ComponentName name) {

		sendMessenger = null;
	}

	protected void onPause() {

		unbindService(this);
		super.onPause();
	}

	public void btnPlay_onclick(View view) {
		Message message = new Message();
		Messenger messenger = message.replyTo;
		message.what = 111;
		try {

			//点击过快可能导致sendMessenger为空
			sendMessenger.send(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void btnPause_onclick(View view) {
		Message message = new Message();
		message.what = 222;
		try {
			sendMessenger.send(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

	public void btnStop_onclick(View view) {
		Message message = new Message();
		message.what = 333;
		try {
			sendMessenger.send(message);
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}
}
