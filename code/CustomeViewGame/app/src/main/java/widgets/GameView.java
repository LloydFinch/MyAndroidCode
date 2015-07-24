package widgets;

import android.content.Context;
import android.graphics.*;
import android.support.v4.util.LogWriter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.venn.customeviewgame.app.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VennUser on 2015/7/21.
 */
public class GameView extends View implements Runnable {

	private Paint gamePaint;
	private int TOTAL_WIDTH, TOTAL_HEIGHT;
	private List<Bitmap> gameBitmapList;
	private int gameHeight;
	private int step;
	private List<Point> pointList;
	private Bitmap buffer;
	private Canvas bufferCanvas;

	public GameView(Context context) {
		super(context);
		init(context, null);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, null);
	}

	private void init(Context context, AttributeSet attrs) {

		gamePaint = new Paint();

		//点击坐标的缓冲区
		pointList = new ArrayList<Point>();

		TOTAL_WIDTH = 500;
		TOTAL_HEIGHT = 1200;

		step = TOTAL_HEIGHT - 10;

		gameBitmapList = new ArrayList<Bitmap>();
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.balloon);
		gameBitmapList.add(bitmap);
	}

	protected void onDraw(Canvas canvas) {

		for (Point point : pointList) {

			//碰撞检测的处理
			if (point.x >= TOTAL_HEIGHT / 2 + 10 && point.x <= TOTAL_HEIGHT / 2 + 150 && point.y > step + 10 && point
					.y < step + 150) {
				pointList.remove(point);

				//点中就回到原位
				step = TOTAL_HEIGHT - 10;
				break;
			}
		}

		bufferCanvas.drawColor(Color.WHITE);

		bufferCanvas.drawBitmap(gameBitmapList.get(0), TOTAL_HEIGHT / 2, step, null);

		canvas.drawBitmap(buffer, 0, 0, null);
	}

	//游戏开始
	public void Start() {
		Thread gameThread = new Thread(this);
		gameThread.start();
	}

	public void run() {
		while (true) {
			if (step > -300) {

				//每次向上移动10个单位
				step -= 10;
			} else {

				//移动出屏幕就回到原位
				step = TOTAL_HEIGHT - 10;
			}

			//每次刷新都会自动清除上次画的内容
			postInvalidate();

			try {

				//每隔100ms刷新一次气球的位置
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//点击到气球就归位到底部
	public boolean onTouchEvent(MotionEvent event) {

		int action = event.getAction();
		switch (action) {
			case MotionEvent.ACTION_DOWN:

				int x = (int) event.getX();
				int y = (int) event.getY();
				pointList.add(new Point(x, y));
		}
		return false;
	}

	//控件尺寸发生变化时回调
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		buffer = null;

		//创建缓冲
		buffer = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

		//当前绘制的内容先会绘制在此画布上
		bufferCanvas = null;
		bufferCanvas = new Canvas(buffer);
	}
}
