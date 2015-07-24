
package com.example.android_day0713_messagerservice.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/13.
 */

//用于进程间通信的服务,采用绑定的方式
public class MessengerService extends Service {

	//采用信使的方式，进行两个应用程序之间的绑定

	private Messenger messenger;

	//用于处理消息，就是服务的内部功能调用
	private Handler receiveHandler = new Handler() {
		public void handleMessage(Message msg) {
			int what = msg.what;
			Log.d("---------->", "messenger handler what:" + what);
		}
	};

	//初始化信使
	public void onCreate() {
		super.onCreate();

		//在服务中用于接收其他程序发送过来的消息，需要采用此种构造方法来创建一个
		//用于接收消息的信使
		messenger = new Messenger(receiveHandler);

		Log.d("---------->", "messenger handler onCreate:");
	}

	public IBinder onBind(Intent intent) {

		Log.d("---------->", "messenger handler onBind:");

		//将信使的绑定接口返回给其他应用程序
		//相当于将手机号给其他人
		//Messenger内部包含了IBinder对象，能够进行应用程序间的通信r
		return messenger.getBinder();
	}
}
