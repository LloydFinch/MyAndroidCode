package com.venn.customview2.app.widgets;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by VennUser on 2015/7/21.
 */

//饼图的实现
public class SectorView extends View {

	private Paint paint;
	private RectF rect;

	public SectorView(Context context) {
		super(context);
		init(context, null);
	}

	public SectorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, null);

	}

	private void init(Context context, AttributeSet attrs) {

		paint = new Paint();
		rect = new RectF(50, 50, 450, 450);
	}

	protected void onDraw(Canvas canvas) {

		//画扇形,实际就是画弧线
		paint.setColor(Color.BLUE);
		//parameter:外接矩形,开始角度,经历的角度(顺时针)
		//起始点与终止点是否链接到圆心
		canvas.drawArc(rect, 0, -180, false, paint);

		paint.setColor(Color.GREEN);
		canvas.drawArc(rect, 0, 180, false, paint);

		paint.setColor(Color.WHITE);

		canvas.drawCircle(rect.centerX(), rect.centerY(), 100, paint);
	}
}
