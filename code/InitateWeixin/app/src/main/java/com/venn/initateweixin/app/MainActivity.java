package com.venn.initateweixin.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.EditText;
import android.widget.ListView;
import com.venn.initateweixin.app.adapter.MessageAdapter;
import com.venn.initateweixin.app.tool.MessageSQLiteOpenHelper;


public class MainActivity extends Activity {

	private EditText editText;
	private ListView listView;
	private MessageAdapter adapter;
	private SubSendThread subSendThread;
	private SQLiteDatabase database;
	private LinearLayout linearLayout;

	private Handler handlerUI = new Handler() {
		public void handleMessage(Message msg) {

			int flag = msg.what;
			String content = (String) msg.obj;
			if (msg.what == 333) {

				//更新适配器信息
				MessageAdapter.addMessage(new String[]{String.valueOf(flag), content});
				adapter.notifyDataSetChanged();

				//放入数据库
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

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		MessageSQLiteOpenHelper openHelper = new MessageSQLiteOpenHelper(this);

		editText = (EditText) this.findViewById(R.id.txt_message);
		listView = (ListView) this.findViewById(R.id.list_content);
		linearLayout = (LinearLayout) this.findViewById(R.id.linear_me);
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_bottom);
		linearLayout.setAnimation(animation);

		adapter = new MessageAdapter(this);
		database = openHelper.getWritableDatabase();
//		Cursor cursor = database.query("messages", null, null, null, null, null, null);
//		if (cursor != null) {
//			while (cursor.moveToNext()) {
//				String content = null, flag = null;
//
//				int indexContent = cursor.getColumnIndex("content");
//				if (indexContent > -1) {
//					content = cursor.getColumnName(indexContent);
//				}
//
//				int indexFlag = cursor.getColumnIndex("flag");
//				if (indexFlag > -1) {
//					flag = cursor.getColumnName(indexFlag);
//				}
//
//				MessageAdapter.addMessage(new String[]{flag, content});
//			}
//		}

		listView.setAdapter(adapter);

		subSendThread = new SubSendThread();
		subSendThread.start();
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void btnSound_onclick(View view) {

		Toast.makeText(this, "暂不支持此功能", Toast.LENGTH_SHORT).show();
	}

	public void btnFace_onclick(View view) {

		Toast.makeText(this, "暂不支持此功能", Toast.LENGTH_SHORT).show();

		linearLayout.animate();
	}

	public void btnSend_onclick(View view) {

		String sendStr = editText.getText().toString();

		//构造信息
		Handler handlerSub = subSendThread.getHandlerSub();
		Message message = handlerSub.obtainMessage();
		message.what = 222;
		message.obj = sendStr;

		//发送
		handlerSub.sendMessage(message);

		//更新适配器信息
		MessageAdapter.addMessage(new String[]{"222", sendStr});
		adapter.notifyDataSetChanged();

		//插入数据库
		ContentValues values = new ContentValues();
		values.put("content", sendStr);
		values.put("time", String.valueOf(System.currentTimeMillis()));
		values.put("flag", "222");
		database.insert(
				"messages",
				null,
				values
		);
	}

	private class SubSendThread extends Thread {
		private Handler handlerSub;

		public Handler getHandlerSub() {
			return handlerSub;
		}

		public void run() {
			Looper.prepare();

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
