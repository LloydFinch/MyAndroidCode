package com.venn.myapplication5.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements Animation.AnimationListener, Runnable {

	private TextView textViewNum, textViewZan;
	private Button button;
	private ImageView imageView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textViewZan = (TextView) this.findViewById(R.id.text_one);
		textViewNum = (TextView) this.findViewById(R.id.text_num);
		imageView = (ImageView) this.findViewById(R.id.image_select);

		button = (Button) this.findViewById(R.id.btn_rotate);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
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

	public void btn_onclick(View view) {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_zan);

		animation.setAnimationListener(this);

		textViewZan.setVisibility(View.VISIBLE);
		textViewZan.setAnimation(animation);
		textViewZan.animate();
	}

	//开始播放
	public void onAnimationStart(Animation animation) {

	}

	//结束播放
	public void onAnimationEnd(Animation animation) {
		textViewZan.setVisibility(View.INVISIBLE);
		int num = Integer.parseInt(textViewNum.getText().toString());

		textViewNum.setText(String.valueOf(num + 1));
	}

	//重复播放
	public void onAnimationRepeat(Animation animation) {

	}

	public void btnRotate_onclick(View view) {

		Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate);
		//button.setAnimation(animation);
		//button.animate();
		button.startAnimation(animation);
	}

	public void image_onclick(View view) {
		Animation anim = view.getAnimation();
		if (anim != null) {
			view.clearAnimation();
		}

		Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_scale);
		view.startAnimation(animation);
	}

	public void btnClick_onclick(View view) {
		Toast.makeText(this, "Clicked Me !", Toast.LENGTH_LONG).show();

		Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
		view.startAnimation(animation);
	}

	public void imageSelec_onlick(View view) {
		Thread thread = new Thread(this);
	}

	public void run() {
		try {
			Thread.sleep(300);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
