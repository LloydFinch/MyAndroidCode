package com.venn.viewselfapp.app;

import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;
import com.venn.viewselfapp.app.widgets.FallStreamView;


public class FallStreamActivity extends ActionBarActivity implements FallStreamView.OnLoadMoreListener {

	private FallStreamView fallStreamView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fall_stream);

		fallStreamView = (FallStreamView) this.findViewById(R.id.view_my);

		fallStreamView.setOnLoadMoreListener(this);
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

	//用来给瀑布流添加控件
	public void btnAddOnclick(View view) {
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.mipmap.ic_launcher);
		fallStreamView.addSubView(imageView);

	}

	public void btnAdd1Onclick(View view) {

		TextView textView = new TextView(this);
		textView.setText("TextView");
		fallStreamView.addSubView(textView);
	}

	public void btnAdd2Onclick(View view) {
		Button button = new Button(this);
		button.setText("Button");
		fallStreamView.addSubView(button);
	}

	public void onBottom(FallStreamView view) {
		for (int i = 0; i < 9; i++) {

			ImageView imageView = new ImageView(this);

			//int viewWidth = getColumn(imageView);

			switch (i % 3) {
				case 0:
					imageView.setImageResource(R.mipmap.p1);
					break;
				case 1:
					imageView.setImageResource(R.mipmap.p2);
					break;
				case 2:
					imageView.setImageResource(R.mipmap.p3);
					break;
			}

//			Drawable drawable = imageView.getDrawable();
//
//			int width = drawable.getIntrinsicWidth();
//
//			int height = drawable.getIntrinsicHeight();
//
//			ViewGroup.LayoutParams params =new ViewGroup.LayoutParams(width,height);

			view.addSubView(imageView);
		}
	}


	//计算单元宽度,从而布局排版合适
	public int getColumn(View view) {
		int width = 0;
		if (view != null) {
			width = view.getWidth();
		}
		return width;
	}
}
