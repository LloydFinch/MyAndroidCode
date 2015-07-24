package com.venn.fallstreamhomework.app.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
import com.venn.fallstreamhomework.app.R;

/**
 * Created by VennUser on 2015/7/20.
 */
public class MyTextView extends TextView {

	private Paint paint;

	public MyTextView(Context context) {
		this(context, null);
	}

	public MyTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, null);
	}

	private void init(Context context, AttributeSet attrs) {
		paint = new Paint();

		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
		int color = typedArray.getColor(R.styleable.MyTextView_myColor, Color.BLUE);
		float dimension = typedArray.getDimension(R.styleable.MyTextView_mySize, 30);

		paint.setColor(color);
		paint.setStrokeWidth(dimension);
		paint.setTextSize(dimension);
	}

	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		int width = this.getWidth();
		int height = this.getHeight();
		//canvas.drawRect(0, 0, width, height, paint);
		paint.setColor(Color.RED);

		canvas.drawText("hello", 40, 40, paint);
	}
}
