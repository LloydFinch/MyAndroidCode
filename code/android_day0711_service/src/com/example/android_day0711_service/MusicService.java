package com.example.android_day0711_service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by VennUser on 2015/7/11.
 */

//音乐播放的服务
public class MusicService extends Service {

	private MediaPlayer player;

	public IBinder onBind(Intent intent) {

		return null;
	}

	//服务第一次启动被调用,只调用一次
	public void onCreate() {

		Log.d("----------->", "onCreate");
		super.onCreate();

		//利用上下文和资源文件创建音乐播放器
		player = MediaPlayer.create(this, R.raw.music);
	}

	//每次startService(intent)调用，此方法都会调用
	//返回值：用来控制服务恢复支持的形式，当服务意外终止，系统可以根据此返回值进行复活
	//当此返回模式为"粘性"时，当服务意外终止，会自动复活，称为粘性服务
	//当设置为粘性返回值，并杀掉此进程，系统会尝试启动该进程并回复服务，再次调用此方法，且
	//参数1不被设置，也就是为null
	public int onStartCommand(Intent intent, int flags, int startId) {


		Log.d("----------->", "onStartCommand" + intent.getIntExtra("value", 0));

		if (intent != null) {
			String control = intent.getStringExtra("control");
			if (control != null) {
				if (control.equals("play")) {
					if (!player.isPlaying()) {
						player.start();
					}
				} else if (control.equals("pause")) {
					if (player != null) {
						player.pause();
					}
				} else if (control.equals("stop")) {
					if (player != null) {
						player.stop();
					}
					player.release();
					player = MediaPlayer.create(this, R.raw.music);
				}
			}
		} else {
			Log.d("----------->", "onStartCommand , intent 为空");
		}

		//返回值控制是否是粘性的
		return START_STICKY;
	}

	//当服务停止或者被关闭调用
	public void onDestroy() {

		Log.d("----------->", "onDestroy");
		if (player != null) {
			if (player.isPlaying() || player.isLooping()) {
				player.stop();
			}

			//释放所有占用的资源
			player.release();
		}

		super.onDestroy();
	}
}
