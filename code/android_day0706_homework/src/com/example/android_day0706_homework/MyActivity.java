
package com.example.android_day0706_homework;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.*;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import com.example.android_day0706_homework.adapter.MessageAdapter;
import com.example.android_day0706_homework.sqlite.MessageSQLiteOnpnHelper;

import java.util.List;

public class MyActivity extends Activity {
	private EditText editText;
	private ListView listView;
	private MessageAdapter adapter;
	private SubSendThread subSendThread;
	private SQLiteDatabase database;
	private Handler handlerUI = new Handler() {
		public void handleMessage(Message msg) {

			int flag = msg.what;
			String content = (String) msg.obj;
			if (msg.what == 333) {
				MessageAdapter.addMessage(new String[]{String.valueOf(flag), content});
				adapter.notifyDataSetChanged();

				ContentValues values = new ContentValues();
				values.put("content", content);
				values.put("time", String.valueOf(System.currentTimeMillis()));
				values.put("flag", String.valueOf(flag));
				database.insert(
						"messages",
						null,
						values
				);
			}
		}
	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		MessageSQLiteOnpnHelper openHelper = new MessageSQLiteOnpnHelper(this);

		editText = (EditText) this.findViewById(R.id.txt_message);
		listView = (ListView) this.findViewById(R.id.list_content);

		adapter = new MessageAdapter(this);
		database = openHelper.getWritableDatabase();
		Cursor cursor = database.query("messages", null, null, null, null, null, null);
		if (cursor != null) {
			while (cursor.moveToNext()) {
				String content = null, flag = null;

				int indexContent = cursor.getColumnIndex("content");
				if (indexContent > -1) {
					content = cursor.getColumnName(indexContent);
				}

				int indexFlag = cursor.getColumnIndex("flag");
				if (indexFlag > -1) {
					flag = cursor.getColumnName(indexFlag);
				}

				MessageAdapter.addMessage(new String[]{flag, content});
			}
		}

		listView.setAdapter(adapter);

		subSendThread = new SubSendThread();
		subSendThread.start();
	}

	public void btnSend_onclick(View view) {
		String sendStr = editText.getText().toString();

		Handler handlerSub = subSendThread.getHandlerSub();
		Message message = handlerSub.obtainMessage();
		message.what = 222;
		message.obj = sendStr;

		handlerSub.sendMessage(message);

		MessageAdapter.addMessage(new String[]{"222", sendStr});
		adapter.notifyDataSetChanged();

		ContentValues values = new ContentValues();
		values.put("content", sendStr);
		values.put("time", String.valueOf(System.currentTimeMillis()));
		values.put("flag", "222");
		database.insert(
				"messages",
				null,
				values
		);

		Looper looper = Looper.myLooper();
		MessageQueue messageQueue = Looper.myQueue();
		Log.d("==========>", "looperMain:" + looper + ",mainMesQueue:" + messageQueue);

	}

	private class SubSendThread extends Thread {
		private Handler handlerSub;

		public Handler getHandlerSub() {
			return handlerSub;
		}

		public void run() {
			Looper.prepare();
			Looper looper = Looper.myLooper();
			MessageQueue messageQueue = Looper.myQueue();
			Log.d("==========>", "looperSub:" + looper + ",subMesQueue:" + messageQueue);

			handlerSub = new Handler() {
				public void handleMessage(Message msg) {
					Message message = handlerUI.obtainMessage();
					message.what = 333;
					message.obj = "子线程的回复";
					handlerUI.sendMessage(message);
				}
			};
			Looper.loop();
		}
	}
}
