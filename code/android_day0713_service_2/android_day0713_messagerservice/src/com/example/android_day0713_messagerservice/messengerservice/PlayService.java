package com.example.android_day0713_messagerservice.messengerservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.*;
import android.util.Log;
import com.example.android_day0713_messagerservice.R;


/**
 * Created by VennUser on 2015/7/13.
 */
public class PlayService extends Service {

	private MediaPlayer mediaPlayer;
	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			int op = msg.what;

			//信使传递发杂数据推荐使用getData()获取
			Bundle data = msg.getData();
			switch (op) {
				case 111:
					if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
						mediaPlayer.start();
						Log.d("----------->", "play");
						if (msg != null) {
							Message message = new Message();
							message.what = 999;
							try {
								msg.replyTo.send(message);
							} catch (RemoteException e) {
								e.printStackTrace();
							}
						}
					}
					break;
				case 222:
					if (mediaPlayer != null && mediaPlayer.isPlaying()) {
						mediaPlayer.pause();
						Log.d("----------->", "pause");
					}
					break;
				case 333:
					mediaPlayer.stop();
					mediaPlayer.release();
					break;
			}
		}
	};

	private Messenger messenger;

	public void onCreate() {
		super.onCreate();

		//使用handler处理接收到的消息
		messenger = new Messenger(handler);
		mediaPlayer = MediaPlayer.create(this, R.raw.music);
	}

	public IBinder onBind(Intent intent) {

		//返回接口
		return messenger.getBinder();
	}

	public void onDestroy() {

		if (mediaPlayer != null) {
			mediaPlayer.stop();
		}
		mediaPlayer.release();
		mediaPlayer = MediaPlayer.create(this, R.raw.music);
		super.onDestroy();
	}
}
