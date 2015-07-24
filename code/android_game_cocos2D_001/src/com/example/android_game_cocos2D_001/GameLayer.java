package com.example.android_game_cocos2D_001;

import org.cocos2d.actions.interval.CCJumpTo;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by VennUser on 2015/7/9.
 */
public class GameLayer extends CCLayer {
	private CCSprite sprite;

	public GameLayer() {

		//创建精灵
		sprite = CCSprite.sprite("game_mali.png");

		//指定精灵初始位置
		CGPoint point = CGPoint.ccp(50, 50);

		//将精灵添加至布景中的指定位置
		sprite.setPosition(point);
		this.addChild(sprite);

		//指定要跳跃到的位置
		CGPoint target = CGPoint.ccp(100, 800);

		//创建一个跳跃动作
		CCJumpTo ccJumpTo = CCJumpTo.action(3, target, 80, 10);

		//精灵执行跳跃动作
		sprite.runAction(ccJumpTo);
	}
}
