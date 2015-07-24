package com.venn.customview2.app.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by VennUser on 2015/7/21.
 */
public class ChartView extends View {
	private Paint linePaint;
	private Paint hLinePaint;
	private TextPaint textPaint;
	private String[] xTitle;
	private String[] yTitle;
	private float descent;
	private float lastYearData[];

	public ChartView(Context context) {
		super(context);
		init(context, null);
	}

	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, null);
	}

	private void init(Context context, AttributeSet attrs) {

		linePaint = new Paint();
		linePaint.setColor(Color.DKGRAY);
		linePaint.setAntiAlias(true);
		linePaint.setStrokeWidth(1);

		//初始化带有虚线的Paint
		hLinePaint = new Paint();
		hLinePaint.setColor(Color.DKGRAY);
		hLinePaint.setAntiAlias(true);
		hLinePaint.setStrokeWidth(1);

		//设置path效果,将线段变为虚线
		//设置虚线的效果:
		// 1 虚线点间距
		// 2 从数组哪个索引开始
		DashPathEffect dashPathEffect = new DashPathEffect(new float[]{2, 2}, 0);
		hLinePaint.setPathEffect(dashPathEffect);

		yTitle = new String[]{"80000", "60000", "40000", "20000", "0"};
		xTitle = new String[]{"1", "2", "3", "4", "5", "6", "7"};

		textPaint = new TextPaint();
		textPaint.setColor(Color.DKGRAY);
		textPaint.setStrokeWidth(1);
		textPaint.setTextAlign(Paint.Align.RIGHT);
		descent = textPaint.descent();

		lastYearData = new float[]{20000, 10000, 50000, 30000, 60000, 40000, 35000};
	}

	protected void onDraw(Canvas canvas) {
		//绘制坐标轴
		//默认的常量:Y轴与屏幕左端的间距,X轴与屏幕底部的间距

		//边距
		int START_Y = 100;
		int START_X = 50;

		//屏幕大小
		int totalWidth = getWidth();
		int totalHeight = getHeight();

		//坐标实际大小
		int rWidth = totalWidth - START_X;
		int rHeight = totalHeight - START_Y;

		//坐标内部边距差
		int topSpan = 20;
		int leftSpan = 20;

		//坐标内容大小
		int yAxisHeight = rHeight - topSpan;
		int xAxisWidth = rWidth - leftSpan;

		int numData = 4;

		//单元格的大小
		int cellXWidth = xAxisWidth / numData;
		int cellYHeight = yAxisHeight / 7;

		//坐标轴
		//x轴
		canvas.drawLine(START_X, START_Y + 3 * cellYHeight, rWidth - 5, START_Y + 3 * cellYHeight, linePaint);

		//y轴
		canvas.drawLine(START_X, START_Y, START_X, rHeight - 5, linePaint);

		for (int i = 0; i < numData; i++) {
			canvas.drawLine(START_X, START_Y + i * cellYHeight, rWidth - 5, START_Y + i * cellYHeight, hLinePaint);
		}

		//y轴标题
		for (int i = 0; i < yTitle.length; i++) {
			canvas.drawText(yTitle[i], START_X - 10, START_Y + i * cellYHeight + descent, textPaint);
		}

		//X轴标题
		for (int i = 0; i < xTitle.length; i++) {
			canvas.drawText(xTitle[i], START_X + i * cellXWidth, rHeight + 10, textPaint);
		}

		//画矩形
		linePaint.setColor(Color.RED);
		for (int i = 0; i < xTitle.length; i++) {

			float demo = lastYearData[i];
			float cc = 80000 - demo;
			float cHeight = cc * yAxisHeight / 80000;
			canvas.drawRect(START_X + i * cellXWidth - 20, 400, START_X + i * cellXWidth + 20, START_Y, linePaint);
		}
	}
}
