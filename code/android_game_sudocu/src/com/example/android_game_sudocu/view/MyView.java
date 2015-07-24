package com.example.android_game_sudocu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by VennUser on 2015/7/14.
 */
public class MyView extends View {

	public MyView(Context context) {
		super(context);
	}

	protected void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawLine(100, 100, 200, 200, paint);
		super.onDraw(canvas);
	}
}
