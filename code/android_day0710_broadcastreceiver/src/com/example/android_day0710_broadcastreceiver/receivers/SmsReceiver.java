package com.example.android_day0710_broadcastreceiver.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * Created by VennUser on 2015/7/10.
 */
public class SmsReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Object[] objects = (Object[]) intent.getExtras().get("pdus");
		for (Object object : objects) {
			byte[] messages = (byte[]) object;
			SmsMessage sms = SmsMessage.createFromPdu(messages);
			String mobile = sms.getOriginatingAddress();
			String content = sms.getMessageBody();
			Date date = new Date(sms.getTimestampMillis());
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String time = format.format(date);

			Log.d("----->", mobile + "-" + content + "-" + time);
			Toast.makeText(context, mobile + "-" + content + "-" + time, Toast.LENGTH_LONG).show();
		}

//////////////////////////////////////////////////////////////////////////
		String messages = intent.getAction();
		if ("android.provider.Telephony.SMS_RECEIVED".equals(messages)) {
			Bundle bundle = intent.getExtras();
			Set<String> keys = bundle.keySet();
			for (String key : keys) {
				Object obj = bundle.get(key);
				Log.d("----->", obj.getClass().toString());
			}

			Object[] pdus = (Object[]) bundle.get("pdus");
			if (pdus != null) {
				for (Object pdu : pdus) {
					//每一个pdu是一个数组,需要解析
					byte[] sms = (byte[]) pdu;
					SmsMessage message = SmsMessage.createFromPdu(sms);
					String content = message.getMessageBody();
					Date date = new Date(message.getTimestampMillis());
					String number = message.getDisplayOriginatingAddress();
				}
			}
		}
	}
}
