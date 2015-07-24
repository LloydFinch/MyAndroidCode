package com.example.android_day0630_SQLiteCountMoney;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.android_day0630_SQLiteCountMoney.database.DBHelper;
import org.apache.commons.logging.Log;

import java.sql.Time;

public class MyActivity extends Activity {

	private DBHelper dbHelper;
	private SQLiteDatabase database;
	private RadioGroup recordType;
	private EditText money;
	private EditText category1;
	private EditText category2;
	private ListView listView;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		recordType = (RadioGroup) this.findViewById(R.id.record_type);
		money = (EditText) this.findViewById(R.id.record_money);
		category1 = (EditText) this.findViewById(R.id.record_category1);
		category2 = (EditText) this.findViewById(R.id.record_category2);
		listView = (ListView) this.findViewById(R.id.listview);

		//open database
		dbHelper = new DBHelper(this);

		//get a rw database,if the storage is full,the db is only readable
		database = dbHelper.getWritableDatabase();

	}

	//save record:insert
	public void btnAddRecord_onclick(View view) {

		//get data
		String moneyCount = money.getText().toString();
		String categoryType1 = category1.getText().toString();
		String categoryType2 = category2.getText().toString();
		int rId = recordType.getCheckedRadioButtonId();
		int rType = 0;
		switch (rId) {
			case R.id.record_type_in:
				rType = 1;
				break;
			case R.id.record_type_out:
				rType = 0;
				break;
		}

		float money = Float.parseFloat(moneyCount);
		long time = System.currentTimeMillis();

		//TODO add record
		//ContentValues like a map,key is column name, value is the value of the column
		ContentValues values = new ContentValues();

		//put record into values
		values.put("record_type", rType);
		values.put("money", money);
		values.put("time", time);
		values.put("category1", categoryType1);
		values.put("category2", categoryType2);

		//insert record into database,return new record's Id
		//if the third param is null or haven't data, the second param will be a row name
		//to  form a SQL sentence
		long rowId = database.insert("records", null, values);
		Toast.makeText(this, "newId:" + rowId, Toast.LENGTH_LONG).show();

	}

	//display data
	protected void onResume() {
		super.onResume();

		//query and display data
		//param: 1 table name 2 row name,null express *
		//       3 where(?) 4 content of ? in param 3
		//       5 group by 6 having 7 order by

		//cursor's position in front of the first record(-1)
		Cursor cursor = database.query("records", //table name
				null, //row name, null express *(all row)
				"record_type = ? and money >= ?", //where
				new String[]{"0", "2000"}, //where condition
				null, //group by
				null, //having
				"time"); //order by
		//use of Cursor
		//traversal(遍历) cursor
//		if (cursor != null) {
//
//			//judge if cursor have next, if have, get
//			while (cursor.moveToNext()) {
//
//				int id = 0;
//				float money = 0;
//				long time = 0;
//				int record_type = 0;
//				String category1 = null;
//				String category2 = null;
//
//				//get index(索引) by row name
//				//if the row name not exist ,return -1
//				int index = cursor.getColumnIndex("id");
//
//				if (index > -1) {
//					//get data by index
//					id = cursor.getInt(index);
//				}
//
//				int indexM = cursor.getColumnIndex("money");
//				if (indexM > -1) {
//					money = cursor.getFloat(indexM);
//				}
//
//				int indexTime = cursor.getColumnIndex("time");
//				if (indexTime > -1) {
//					time = cursor.getLong(indexTime);
//				}
//
//				int indexC1 = cursor.getColumnIndex("category1");
//				if (indexC1 > -1) {
//					category1 = cursor.getString(indexC1);
//				}
//
//				int indexC2 = cursor.getColumnIndex("category2");
//				if (indexC2 > -1) {
//					category2 = cursor.getString(indexC2);
//				}
//				android.util.Log.d("----->", "" + id + " " + time + " " + money + " " + category1 + " " + category2);
//			}
//			//must close cursor
//			cursor.close();
//		}
		//showCursor(cursor);
		if (listView != null) {
			SimpleCursorAdapter adapter
					= new SimpleCursorAdapter(
					this,
					android.R.layout.simple_list_item_1,
					cursor, //Cursor, if not assign, show empty
					new String[]{"money"},// row name
					new int[]{android.R.id.text1}//
			);
			if (cursor != null) {
				//替换Cursor(changeCursor(Cursor))
				adapter.swapCursor(cursor);
			}
			listView.setAdapter(adapter);
		}

	}

	private void showCursor(Cursor cursor) {
		if (cursor != null) {

			//judge if cursor have next, if have, get
			while (cursor.moveToNext()) {

				int id = 0;
				float money = 0;
				long time = 0;
				String category1 = null;
				String category2 = null;

				//get index(索引) by row name
				//if the row name not exist ,return -1
				int index = cursor.getColumnIndex("id");

				if (index > -1) {
					//get data by index
					id = cursor.getInt(index);
				}

				int indexM = cursor.getColumnIndex("money");
				if (indexM > -1) {
					money = cursor.getFloat(indexM);
				}

				int indexTime = cursor.getColumnIndex("time");
				if (indexTime > -1) {
					time = cursor.getLong(indexTime);
				}

				int indexC1 = cursor.getColumnIndex("category1");
				if (indexC1 > -1) {
					category1 = cursor.getString(indexC1);
				}

				int indexC2 = cursor.getColumnIndex("category2");
				if (indexC2 > -1) {
					category2 = cursor.getString(indexC2);
				}
				android.util.Log.d("----->", "" + id + " " + time + " " + money + " " + category1 + " " + category2);
			}
			//must close cursor
			cursor.close();
		}
	}

	protected void onDestroy() {

		//close database
		database.close();
		dbHelper.close();

		super.onDestroy();
	}
}
