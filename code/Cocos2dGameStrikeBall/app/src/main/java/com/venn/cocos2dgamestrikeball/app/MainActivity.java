package com.venn.cocos2dgamestrikeball.app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;


public class MainActivity extends ActionBarActivity {

	private CCGLSurfaceView surfaceView;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//游戏容器视图
		surfaceView = new CCGLSurfaceView(this);
		setContentView(surfaceView);

		//导演
		CCDirector director = CCDirector.sharedDirector();
		director.attachInView(surfaceView);
		director.setDisplayFPS(true);
		director.setAnimationInterval(1 / 30);

		//场景
		CCScene scene = CCScene.node();

		//布景层
		GameLayer gameLayer = new GameLayer(BitmapFactory.decodeResource(getResources(), R.mipmap.balloon));

		//添加布景
		scene.addChild(gameLayer);

		//运行游戏
		director.runWithScene(scene);
	}


	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

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
}
