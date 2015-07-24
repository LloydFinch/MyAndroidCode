package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.View;
import com.android.internal.telephony.ITelephony;
import com.android.internal.telephony.R;

import java.lang.reflect.Method;

public class MyActivity extends Activity {
	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void btnAnswer_onclick(View view) {
		answer();
	}

	public void btnHung_onclick(View view) {
		hung();
	}

	public void btnEndCall_onclick(View view) {
		endCall();
	}

	private void answer() {
		try {
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
			IBinder iBinder = (IBinder) method.invoke(null, new Object[]{TELEPHONY_SERVICE});
			ITelephony iTelephony = ITelephony.Stub.asInterface(iBinder);
			iTelephony.answerRingingCall();
		} catch (Exception e) {
			e.printStackTrace();
			try{
				Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
				KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
				intent.putExtra("android.intent.extra.KEY_EVENT", keyEvent);
				sendOrderedBroadcast(intent,"android.permission.CALL_PRIVILEGED");
			} catch (Exception e2) {
				Intent mediaButtonIntent = new Intent(Intent.ACTION_MEDIA_BUTTON);
				KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_HEADSETHOOK);
				mediaButtonIntent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
				sendOrderedBroadcast(mediaButtonIntent, null);
			}
		}
	}

	private void hung() {
		try {
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
			IBinder iBinder = (IBinder) method.invoke(null, new Object[]{TELEPHONY_SERVICE});
			ITelephony iTelephony = ITelephony.Stub.asInterface(iBinder);
			iTelephony.endCall();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void endCall() {

		try {
			Method method = Class.forName("android.os.ServiceManager").getMethod("getService", String.class);
			IBinder iBinder = (IBinder) method.invoke(null, new Object[]{TELEPHONY_SERVICE});
			ITelephony iTelephony = ITelephony.Stub.asInterface(iBinder);
			iTelephony.endCall();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
