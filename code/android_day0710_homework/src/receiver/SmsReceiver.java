package receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import contactsTool.ContactsTool;
import entity.Message;
import fragment.InterceptFragment;

import java.util.List;

/**
 * Created by VennUser on 2015/7/11.
 */
public class SmsReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		Bundle bundle = intent.getExtras();
		Object[] objects = (Object[]) bundle.get("pdus");
		for (Object object : objects) {
			Message message = new Message();

			byte[] sms = (byte[]) object;
			SmsMessage smsMessage = SmsMessage.createFromPdu(sms);

			String number = smsMessage.getOriginatingAddress();
			message.setNumber(number);
			String name = ContactsTool.getMessageName(context, number);
			message.setPerson(name);
			Log.d("------>", name + "---" + number);
			Log.d("------>", number.substring(3));
			String content = smsMessage.getMessageBody();
			message.setContent(content);
			long time = smsMessage.getTimestampMillis();
			message.setTime(time);

			List<Message> interceptList = ContactsTool.selectIntercept();
			for (Message m : interceptList) {
				if (number.equals(m.getNumber()) || number.substring(3).equals(m.getNumber()) || number.equals(m.getNumber().substring(3))) {
					ContactsTool.addIntercept(context, message);
				}
			}
		}
	}
}
