package com.venn.viewselfapp.app.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import com.venn.viewselfapp.app.R;

/**
 * Created by VennUser on 2015/7/20.
 */

//自定义View继承已有的View控件,调整样式和功能
public class Notepad extends EditText {


	//自定义View需要重写构造方法
	//用于在代码中创建控件时调用的构造,
	//所有的控件在代码中创建的时候都是通过一个参数的构造方法实现的
	public Notepad(Context context) {
		this(context, null);
	}

	//包含AttributeSet参数的构造都是在xml中使用的
	//此参数就是在xml中定义的属性
	public Notepad(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context, attrs);
	}

	//声明划线的Paint
	private Paint linePaint;

	//自定义控件内部的初始化方法
	private void init(Context context, AttributeSet attrs) {
		linePaint = new Paint();

		int lineColor = Color.RED;
		float lineWidth = 5.0f;

		//属性集合获取颜色
		if (attrs != null) {
			//获取自定义的属性集合的属性值,属性值必须要回收
			TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Notepad);

			//获取自定义属性集合中的自定义颜色
			lineColor = typedArray.getColor(R.styleable.Notepad_lineColor, Color.RED);

			lineWidth = typedArray.getDimension(R.styleable.Notepad_lineWidth, 5.0f);
			//回收属性集合
			typedArray.recycle();

		}

		//线条宽度
		linePaint.setStrokeWidth(lineWidth);

		//线条颜色
		linePaint.setColor(lineColor);
	}

	//自定义UI控件的显示,通过onDraw()重绘控件外观
	//先画出来的显示在下面,后画出来的显示在上面
	protected void onDraw(Canvas canvas) {

		//Android默认每隔光标闪烁一次的时间调用一次该方法
		Log.d("==========>", "onDraw");
		//super.onDraw(canvas);

		//Paint就是绘制的样式设置,不能在onDraw()中创建,要而在控件的初始化之后

		//获取View的宽度,所有控件都能在onDraw()中调用,只有在控件排版之后才有效
		int width = this.getWidth();

		//获取一行的高度
		int lineHeight = this.getLineHeight();

		//获取上边距
		int paddingTop = getPaddingTop();

		//获取下边距
		int paddingBottom = getPaddingBottom();

		int paddingLeft = getPaddingLeft();

		int paddingRight = getPaddingRight();

		int height = getHeight();

		///计算屏幕容许的行数
		int lineNum = (height - paddingTop - paddingBottom) / lineHeight;

		//获取实际内容的行数
		int lineCount = this.getLineCount();

		lineNum = lineNum > lineCount ? lineNum : lineCount;

		//lineNum = Math.max(lineCount, lineNum);

		for (int i = 0; i < lineNum; i++) {
			int currentHeight = (i + 1) * lineHeight + paddingTop;
			canvas.drawLine(paddingLeft, currentHeight, width - paddingRight, currentHeight, linePaint);
		}
	}
}
