package com.example.android_day0711_service;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import java.net.HttpURLConnection;

/**
 * Created by VennUser on 2015/7/11.
 */
public class BindActivity extends Activity implements ServiceConnection {

	private PlayerService.MyBinder controler;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bind);

		//进行服务的绑定,使得当前Activity可以直接操作Service内部的成员
		Intent intent = new Intent(this, PlayerService.class);

		//ServiceConnection接口,用来获取服务内部的对象,获取的对象就可以
		//调用服务内部的方法
		boolean b = bindService(intent, this, BIND_AUTO_CREATE);
	}

	//服务连接到当前Activity时调用,相当于绑定成功,成功则可以使用服务内部的参数.
	// 即参数2,即绑定的服务的onBind()返回的对象
	public void onServiceConnected(ComponentName name, IBinder service) {

		//TODO 接收服务内部对象
		controler = (PlayerService.MyBinder) service;
	}

	//与当前Activity绑定断开时调用,
	public void onServiceDisconnected(ComponentName name) {
		controler = null;
	}

	public void btnPlay2_onclick(View view) {
		if (controler != null) {
			controler.play();
		}
	}

	public void btnPause2_onclick(View view) {
		if (controler != null) {
			controler.pause();
		}
	}

	public void btnStop2_onclick(View view) {
		if (controler != null) {
			controler.stop();
		}
	}

	protected void onDestroy() {

		//取消绑定
		//unbindService(this);
		super.onDestroy();
	}
}