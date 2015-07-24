package com.example.android_day0630_homework;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import com.example.android_day0630_homework.Entity.Account;
import com.example.android_day0630_homework.MyAdapter.ContentAdapter;
import com.example.android_day0630_homework.SQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends Activity implements RadioGroup.OnCheckedChangeListener {
	private EditText txtCategory, txtMoney;
	private RadioGroup radiogroupType;
	private int type = 0;
	private SQLiteDatabase database;
	private List<Object> datas;
	private ListView listViewContent;
	private List<Account> accounts;
	private ContentAdapter adapter;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		radiogroupType = (RadioGroup) this.findViewById(R.id.radiogroup_type);
		txtCategory = (EditText) this.findViewById(R.id.txt_category);
		txtMoney = (EditText) this.findViewById(R.id.txt_count);
		listViewContent = (ListView) this.findViewById(R.id.list_content);

		//get database which is rw
		database = new DBHelper(this).getWritableDatabase();

		accounts = selectData(database, 0);
		adapter = new ContentAdapter(this, accounts);
		listViewContent.setAdapter(adapter);

		//register contextMenu for listViewContent
		registerForContextMenu(listViewContent);

		radiogroupType.setOnCheckedChangeListener(this);
	}

	public void btnadd_onclick(View view) {
		int currentType = type;
		String category = txtCategory.getText().toString();
		float money = Float.parseFloat(txtMoney.getText().toString());

		Account account = new Account(0, System.currentTimeMillis(), money, currentType, category);
		addData(database, account);
		Toast.makeText(this, "已成功添加数据", Toast.LENGTH_SHORT).show();
		btnok_onclick(view);
	}

	public void btndelete_onclick(View view) {

	}

	public void btnok_onclick(View view) {
		ContentAdapter.addDatas(selectData(database, type));
		adapter.notifyDataSetChanged();
	}

	//add
	private void addData(SQLiteDatabase sqLiteDatabase, Account account) {

		//create values to put data by the form of key-value
		ContentValues values = new ContentValues();


		values.put("time", account.getTime());
		values.put("money", account.getMoney());
		values.put("type", account.getType());
		values.put("category", account.getCategory());

		//insert values into database
		sqLiteDatabase.insert("account", null, values);
	}

	//delete by id
	private void deleteData(SQLiteDatabase sqLiteDatabase, Account account) {
		//delete data by id
		String id = String.valueOf(account.getId());
		sqLiteDatabase.delete("account", "id = ?", new String[]{id});
	}

	//select
	private List<Account> selectData(SQLiteDatabase sqLiteDatabase, int type) {

		List<Account> datas = new ArrayList<Account>();
		Account account = null;

		//select all data and order by id
		Cursor cursor = sqLiteDatabase.query("account", null,
				null,
				null,
				null, null, "id");

		if (cursor != null) {
			while (cursor.moveToNext()) {
				account = new Account();
				int id, datatype;
				long time;
				float money;
				String category;

				//get data by cursor
				int indexID = cursor.getColumnIndex("id");
				if (indexID > -1) {
					id = cursor.getInt(indexID);
					account.setId(id);
				}

				int indexTime = cursor.getColumnIndex("time");
				if (indexTime > -1) {
					time = cursor.getLong(indexTime);
					account.setTime(time);
				}

				int indexMoney = cursor.getColumnIndex("money");
				if (indexMoney > -1) {
					money = cursor.getFloat(indexMoney);
					account.setMoney(money);
				}

				int indexType = cursor.getColumnIndex("type");
				if (indexType > -1) {
					datatype = cursor.getInt(indexType);
					account.setType(datatype);
				}

				int indexCategory = cursor.getColumnIndex("category");
				if (indexCategory > -1) {
					category = cursor.getString(indexCategory);
					account.setCategory(category);
				}
				datas.add(account);
			}
		}
		return datas;
	}

	//update
	private void updateData(SQLiteDatabase sqLiteDatabase) {

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
			case R.id.radio_type_in:
				type = 1;
				break;
			case R.id.radio_type_out:
				type = 0;
				break;
		}
	}

	//create context menu
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_context, menu);
	}

	public boolean onContextItemSelected(MenuItem item) {
		ContextMenu.ContextMenuInfo contextMenuInfo = item.getMenuInfo();
		AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = null;
		int position = -1;
		if (contextMenuInfo != null && contextMenuInfo instanceof AdapterView.AdapterContextMenuInfo) {
			adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) contextMenuInfo;
			position = adapterContextMenuInfo.position;
		}
		switch (item.getItemId()) {
			case R.id.edit:
				break;
			case R.id.delete:
				ContentAdapter.removeData(position);
				adapter.notifyDataSetChanged();
				break;
		}
		return true;
	}
}
