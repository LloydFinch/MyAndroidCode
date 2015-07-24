package com.example.android_day0710_broadcastreceiver.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by VennUser on 2015/7/10.
 */
//用于检测手机网络变化的广播接收者
public class NetWorkReceiver extends BroadcastReceiver
{
	//当接收到广播时，自动回调此方法，最多执行时长:10秒
	//param: context 用来启动Service或者发通知 intent 代表广播的数据信息
	public void onReceive(Context context, Intent intent)
	{
		String action = intent.getAction();

		//判断action的类型
		if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action))
		{
			//如果是网络变化，进行处理

			//返回的都是XxxxManager,参数都是XXXXX_Service
			//大部分使用intent获取信息，网络连接建议使用ConnectivityManager

			//intent获取广播消息
			Log.d("NetWork", "网络广播:" + intent.getExtras());

			//网络的extra key 定义在ConnectivityManager中
			//EXTRA_NETWORK_INFO 描述当前网络的状态
			NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
			NetworkInfo.State state = info.getState();

			//断开连接的原因
			info.getReason();

			//新版本获取网络连接
			ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

			//获取当前网络信息
			NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
			if (activeNetworkInfo != null)
			{
				String typeName = activeNetworkInfo.getTypeName();
				String subtypeName = activeNetworkInfo.getSubtypeName();
				Toast.makeText(context, "当前连接" + typeName + "--" + subtypeName, Toast.LENGTH_LONG).show();
			} else
			{
				Toast.makeText(context, "无连接", Toast.LENGTH_LONG).show();
			}
		}
	}
}
