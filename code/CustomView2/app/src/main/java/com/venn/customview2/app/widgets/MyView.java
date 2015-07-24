package com.venn.customview2.app.widgets;

import android.content.Context;
import android.graphics.*;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.venn.customview2.app.R;

/**
 * Created by VennUser on 2015/7/21.
 */
public class MyView extends View {

	private Paint linePaint;
	private Paint circlePaint;
	private RectF rectF;
	private TextPaint textPaint;
	private Bitmap bitmap;
	private Rect src;
	private Rect dst;
	private int maxWidth;
	private int maxHeight;

	//代码调用
	public MyView(Context context) {
		super(context);
		init(context, null);
	}

	//xml布局使用
	public MyView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, null);
	}

	private void init(Context context, AttributeSet attrs) {
		linePaint = new Paint();
		rectF = new RectF(50, 50, 500, 400);

		//抗锯齿,使得画出的线更平滑
		linePaint.setAntiAlias(true);

		linePaint.setColor(Color.YELLOW);

		//线宽为1个像素
		linePaint.setStrokeWidth(1);

		textPaint = new TextPaint();
		textPaint.setColor(Color.BLUE);
		textPaint.setTextSize(30);

		//加载图片资源,用Context获取
		bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.gem_0022);

	}

	//测量自身宽高,通常由父容器调用
	//参数使用MeasureSpec类的方法才可以获取信息
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		//获取宽度
		int width = MeasureSpec.getSize(widthMeasureSpec);
		Log.d("==========>", "Width: " + width);

		//获取尺寸的模式,模式的常量在MeasureSpec中定义
		int wMode = MeasureSpec.getMode(widthMeasureSpec);
		switch (wMode) {
			case MeasureSpec.AT_MOST: //对应wrap_content,需要进行排版
				Log.d("==========>", "wMode: AT_MOST");
				maxWidth = bitmap.getWidth() + 20;
				break;
			case MeasureSpec.EXACTLY:
				Log.d("==========>", "wMode: EXACTLY");
				break;
			case MeasureSpec.UNSPECIFIED:
				Log.d("==========>", "wMode: UNSPECIFIED");
				break;
		}

		int height = MeasureSpec.getSize(heightMeasureSpec);
		Log.d("==========>", "Width: " + height);

		int hMode = MeasureSpec.getMode(heightMeasureSpec);
		switch (hMode) {
			case MeasureSpec.AT_MOST: //对应wrap_content,需要进行排版
				Log.d("==========>", "hMode: AT_MOST");
				maxHeight = bitmap.getHeight();
				break;
			case MeasureSpec.EXACTLY:
				Log.d("==========>", "hMode: EXACTLY");
				break;
			case MeasureSpec.UNSPECIFIED:
				Log.d("==========>", "hMode: UNSPECIFIED");
				break;
		}

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}



	//画自身内容
	protected void onDraw(Canvas canvas) {
		//View自身的onDraw()是空的,可以不调

		//画线
		//drawLine(canvas);

		//画矩形
		//drawRect(canvas, color, count);

		//画文本
		//drawText(canvas);

		//画图片
		drawBitmap(canvas);
	}

	private void drawBitmap(Canvas canvas) {
		canvas.drawBitmap(bitmap, 10, 50, linePaint);

		//按照缩放方式绘制的图片
		src = null;
		dst = new Rect(10, 100, 200, 50);

		//src表示裁剪区域,null表示整个bitmap
		//dst表示用于显示的目标区域
		canvas.drawBitmap(bitmap, src, dst, null);

		//裁剪方式绘制图片
		int bw = bitmap.getWidth();
		int bh = bitmap.getHeight();
		src = new Rect(0, 0, bw / 6, bh);

		dst.left = 10;
		dst.top = 50;
		dst.right = 210;
		dst.bottom = 400;

		canvas.drawColor(Color.WHITE);
		canvas.drawBitmap(bitmap, src, dst, null);
	}

	private void drawLine(Canvas canvas) {//按照ARGB颜色值进行绘制
		canvas.drawARGB(255, 255, 0, 0);
		canvas.drawColor(Color.WHITE);

		//画线的部分
		canvas.drawLine(-10, -30, 200, 10, linePaint);

		//绘制渐变线条
		int color = 0;

		int count = 0xff / 255;
//		int red = Color.RED / 50;
//		int green = Color.GREEN / 50;
//		int blue = Color.BLUE / 50;

//      int rgb = Color.rgb(red, green, blue);

		for (int i = 0; i < 50; i++) {

			linePaint.setColor(Color.rgb(color, color, color));
			canvas.drawLine(10, 50 + i, 200, 150 + 2 * i, linePaint);

			color += count;
		}
	}

	private int drawCircle(Canvas canvas, int color, int count) {//通过Paint指定是填充还是空心
		linePaint.setColor(Color.RED);
		//linePaint.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(100, 100, 50, linePaint);

		canvas.drawColor(Color.WHITE);

		for (int i = 0; i < 255; i++) {
//			switch (i % 5) {
//				case 0:
//					linePaint.setColor(Color.RED);
//					break;
//				case 1:
//					linePaint.setColor(Color.BLUE);
//					break;
//				case 2:
//					linePaint.setColor(Color.GREEN);
//					break;
//				case 3:
//					linePaint.setColor(Color.YELLOW);
//					break;
//				case 4:
//					linePaint.setColor(Color.DKGRAY);
//					break;
//			}
			linePaint.setColor(Color.rgb(color, color, color));
			color += count;
			canvas.drawCircle(300, 300, 255 - i * 1, linePaint);
		}
		return color;
	}

	private void drawRect(Canvas canvas, int color, int count) {
		linePaint.setColor(Color.RED);
		for (int i = 0; i < 255; i++) {
			linePaint.setColor(Color.rgb(color, color, color));
			color += count;
			canvas.drawRect(50 + i * 1, 50 + i * 1, 300 - i * 1, 400 - i * 1, linePaint);
		}
	}

	private void drawText(Canvas canvas) {
		rectF.left = 50;
		rectF.top = 50;
		rectF.right = 500;
		rectF.bottom = 400;

		//绘制圆角矩形
		linePaint.setStyle(Paint.Style.STROKE);
		linePaint.setColor(Color.RED);
		canvas.drawRoundRect(rectF, 15, 15, linePaint);

		//绘制文字
		linePaint.setColor(Color.BLUE);

		//以中心点绘制文本,使用TextPaint的TextAlign属性
		//中间对齐
		textPaint.setTextAlign(Paint.Align.CENTER);

		//可以用FontMetrics来设置字体对应某个点
		Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
		float descent = fontMetrics.descent;

		canvas.drawText("此乃圆角矩形", 275, 225 + descent, textPaint);

		//左对齐
		textPaint.setTextAlign(Paint.Align.LEFT);

		canvas.drawText("开始游戏", 400, 400, textPaint);
		canvas.drawText("新游戏", 400, 450, textPaint);
		canvas.drawText("设置", 400, 500, textPaint);
		canvas.drawText("帮助", 400, 550, textPaint);
	}

	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}
}
