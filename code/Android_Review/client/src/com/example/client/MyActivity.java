package com.example.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.example.client.aidl.service;

public class MyActivity extends Activity implements ServiceConnection {
	/**
	 * Called when the activity is first created.
	 */
	private service s;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = new Intent(".app.Service");
		this.bindService(intent, this, BIND_AUTO_CREATE);
	}

	public void onServiceConnected(ComponentName name, IBinder service) {
		s = com.example.client.aidl.service.Stub.asInterface(service);
		try {
			s.send();
		}
		catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void onServiceDisconnected(ComponentName name) {

	}
}
