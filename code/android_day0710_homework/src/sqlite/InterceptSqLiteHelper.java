package sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by VennUser on 2015/7/10.
 */
public class InterceptSqLiteHelper extends SQLiteOpenHelper {
	public InterceptSqLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table intercept(_id integer primary key autoincrement, sender text, number text, content text, time long)");
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}
