package com.venn.fallstreamhomework.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.venn.fallstreamhomework.app.widgets.FallStreamView;


public class FallStreamActivity extends ActionBarActivity {

	private FallStreamView fallStreamView;
	private EditText editText, editText2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fall_stream);

		fallStreamView = (FallStreamView) this.findViewById(R.id.layout_fall);
		editText = (EditText) this.findViewById(R.id.txt_columns);
		editText2 = (EditText) this.findViewById(R.id.txt_width);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_fall_stream, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public void btnAddOnclick(View view) {

		try {
			fallStreamView.setColumns(Integer.parseInt(editText.getText().toString().trim()), Integer.parseInt
					(editText2.getText().toString().trim()), this);
		}
		catch (Exception e) {
			Toast.makeText(this, "输入数据不合法", Toast.LENGTH_SHORT).show();
		}
	}
}
