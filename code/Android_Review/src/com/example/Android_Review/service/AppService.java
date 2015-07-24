package com.example.Android_Review.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;
import com.example.Android_Review.aidl.service;

/**
 * Created by VennUser on 2015/7/23.
 */
public class AppService extends Service {

	private Messenger messenger;

	private Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			super.handleMessage(msg);
		}
	};

	public void onCreate() {

		messenger = new Messenger(handler);
		super.onCreate();
	}

	public IBinder onBind(Intent intent) {
		//return messenger.getBinder();

		return new SubAidl();
	}

	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	public class MyBinder extends Binder {

	}

	public class SubAidl extends service.Stub {

		public void send() throws RemoteException {

		}
	}
}
