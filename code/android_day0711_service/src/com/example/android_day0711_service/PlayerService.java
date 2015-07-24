package com.example.android_day0711_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

/**
 * Created by VennUser on 2015/7/11.
 */

//绑定方式的服务,通过上下文的bindService()绑定
//取消绑定则服务自动销毁
public class PlayerService extends Service {

	private MediaPlayer player;
	private String nextUrl;

	//第一次绑定前，指定BIND_AUTO_CREATE，就可以自动创建服务
	public void onCreate() {
		player = MediaPlayer.create(this, R.raw.music);
		super.onCreate();
	}

	//返回的IBinder就是onServiceConnection()接收的第二个参数
	//用于应用程序组件与这个Service传递消息
	public IBinder onBind(Intent intent) {

		//直接返回Binder子类的对象
		return new MyBinder();
	}

	public void onDestroy() {
		if (player != null) {
			player.stop();
			player.release();
		}
		super.onDestroy();
	}

	//用于绑定的操作，将这个类返回，就可以通过该对象操作内部信息
	public class MyBinder extends Binder {

		//Binder的子类，在绑定服务的情况下，不需要重写任何方法，只需要定义自己的方法
		public void play() {
			player.start();
		}

		public void pause() {
			player.pause();
		}

		public void stop() {
			player.stop();
			player.release();
			player = MediaPlayer.create(PlayerService.this, R.raw.music);
		}

		//总时间
		public long getDuration() {
			int duration = player.getDuration();
			return duration;
		}

		//当前播放时间
		public long getCurrentProgress() {
			int currentProgress = player.getCurrentPosition();
			return currentProgress;
		}

		public void setNext(String next) {
			nextUrl = next;
		}
	}
}
