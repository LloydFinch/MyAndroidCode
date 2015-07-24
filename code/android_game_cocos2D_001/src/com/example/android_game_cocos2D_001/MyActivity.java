package com.example.android_game_cocos2D_001;

import android.app.Activity;
import android.os.Bundle;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.opengl.CCGLSurfaceView;

public class MyActivity extends Activity {

	private CCGLSurfaceView view = null;

	/**
	 * Called when the activity is first created.
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//创建游戏布局
		view = new CCGLSurfaceView(this);
		//view.setBackgroundResource(R.drawable.layer1);

		//添加游戏布局
		setContentView(view);

		//创建游戏导演
		CCDirector director = CCDirector.sharedDirector();

		//导演关联游戏
		director.attachInView(view);

		//设置游戏的FPS
		director.setDisplayFPS(true);
		director.setAnimationInterval(1 / 30.0);

		//创建游戏场景
		CCScene scene = CCScene.node();

		//创建游戏布景
		//GameLayer gameLayer = new GameLayer();
		GameLayer2 gameLayer = new GameLayer2();

		//将布景添加至场景中
		scene.addChild(gameLayer);

		//导演指定游戏开幕
		director.runWithScene(scene);
	}
}
