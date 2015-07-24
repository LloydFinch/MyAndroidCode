package com.venn.myapplication6.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.graphics.drawable.AnimationDrawable;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.SimpleAdapter;


public class PathAnimation extends ActionBarActivity {

	private Button btnRotate, btnStar1, btnStar2, btnStar3, btnStar4, btnStar5, btnStar6;
	private boolean isReturn = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_path_animation);

		btnRotate = (Button) this.findViewById(R.id.btn_rotate);
		btnStar1 = (Button) this.findViewById(R.id.btn_star11);
		btnStar2 = (Button) this.findViewById(R.id.btn_star12);
		btnStar3 = (Button) this.findViewById(R.id.btn_star13);
		btnStar4 = (Button) this.findViewById(R.id.btn_star14);
		btnStar5 = (Button) this.findViewById(R.id.btn_star15);
		btnStar6 = (Button) this.findViewById(R.id.btn_star16);

	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_path_animation, menu);
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

	public void btnRotateOnclick(View view) {

		Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_btn);
		btnRotate.startAnimation(animation);

		Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_star1);
		Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_star2);
		Animation animation3 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_star3);
		Animation animation4 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_star4);
		Animation animation5 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_star5);
		Animation animation6 = AnimationUtils.loadAnimation(this, R.anim.anim_rotate_star6);

		Animation animation11 = AnimationUtils.loadAnimation(this, R.anim.anim_return_star1);
		Animation animation12 = AnimationUtils.loadAnimation(this, R.anim.anim_return_star2);
		Animation animation13 = AnimationUtils.loadAnimation(this, R.anim.anim_return_star3);
		Animation animation14 = AnimationUtils.loadAnimation(this, R.anim.anim_return_star4);
		Animation animation15 = AnimationUtils.loadAnimation(this, R.anim.anim_return_star5);
		Animation animation16 = AnimationUtils.loadAnimation(this, R.anim.anim_return_star6);

//		Animator animator1 = AnimatorInflater.loadAnimator(this, R.animator.animator_star1);
//		Animator animator2 = AnimatorInflater.loadAnimator(this, R.animator.animator_star2);
//		Animator animator3 = AnimatorInflater.loadAnimator(this, R.animator.animator_star3);
//		Animator animator4 = AnimatorInflater.loadAnimator(this, R.animator.animator_star4);
//		Animator animator5 = AnimatorInflater.loadAnimator(this, R.animator.animator_star5);
//		Animator animator6 = AnimatorInflater.loadAnimator(this, R.animator.animator_star6);
//
//		animator1.setTarget(btnStar1);
//		animator2.setTarget(btnStar2);
//		animator3.setTarget(btnStar3);
//		animator4.setTarget(btnStar4);
//		animator5.setTarget(btnStar5);
//		animator6.setTarget(btnStar6);


		if (!isReturn) {
			btnStar1.startAnimation(animation1);
			btnStar1.setVisibility(View.VISIBLE);

			btnStar2.startAnimation(animation2);
			btnStar2.setVisibility(View.VISIBLE);

			btnStar3.startAnimation(animation3);
			btnStar3.setVisibility(View.VISIBLE);

			btnStar4.startAnimation(animation4);
			btnStar4.setVisibility(View.VISIBLE);

			btnStar5.startAnimation(animation5);
			btnStar5.setVisibility(View.VISIBLE);

			btnStar6.startAnimation(animation6);
			btnStar6.setVisibility(View.VISIBLE);
		} else {
			btnStar1.setAnimation(animation11);
			btnStar1.setVisibility(View.INVISIBLE);

			btnStar2.setAnimation(animation12);
			btnStar2.setVisibility(View.INVISIBLE);

			btnStar3.setAnimation(animation13);
			btnStar3.setVisibility(View.INVISIBLE);

			btnStar4.setAnimation(animation14);
			btnStar4.setVisibility(View.INVISIBLE);

			btnStar5.setAnimation(animation15);
			btnStar5.setVisibility(View.INVISIBLE);

			btnStar6.setAnimation(animation16);
			btnStar6.setVisibility(View.INVISIBLE);
		}
		isReturn = !isReturn;

//		animator1.start();
//		animator2.start();
//		animator3.start();
//		animator4.start();
//		animator5.start();
//		animator6.start();

	}
}
