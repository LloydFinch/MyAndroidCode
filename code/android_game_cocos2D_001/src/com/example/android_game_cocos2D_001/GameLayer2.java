package com.example.android_game_cocos2D_001;

import android.view.MotionEvent;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.interval.*;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * Created by VennUser on 2015/7/12.
 */
public class GameLayer2 extends CCLayer {
	private CCSprite sprite;

	public GameLayer2() {

		this.setIsTouchEnabled(true);

		sprite = CCSprite.sprite("car.png");
		CGPoint position = CGPoint.ccp(500, 300);
		sprite.setPosition(position);

		this.addChild(sprite);

		CGPoint targetPoint = CGPoint.ccp(600, 100);
		//CCMoveTo moveTo = CCMoveTo.action(3, targetPoint);

		CCMoveBy moveBy = CCMoveBy.action(2, targetPoint);

		CCRotateTo rotateTo = CCRotateTo.action(1, 180);

		CCRotateBy rotateBy = CCRotateBy.action(1, 180);

		CCFadeIn fadeIn = CCFadeIn.action(2);

		CCFadeOut fadeOut = CCFadeOut.action(2);

		CGPoint point1 = CGPoint.ccp(-300, 0);
		CGPoint point2 = CGPoint.ccp(300, 0);
		CCMoveBy moveBy1 = CCMoveBy.action(2, point1);
		CCMoveBy moveBy2 = CCMoveBy.action(2, point2);

		CCScaleTo scaleTo = CCScaleTo.action(2, 3 / 4);
		CCSpawn spawn = CCSpawn.actions(moveBy, rotateTo, scaleTo);

		CCSequence sequence = CCSequence.actions(fadeIn, spawn, rotateBy);

		CCSequence sequence1 = CCSequence.actions(moveBy1, moveBy2);

		CCRepeat repeat = CCRepeat.action(sequence1, 3);

		CCRepeatForever ccRepeatForever = CCRepeatForever.action(sequence1);

		sprite.runAction(ccRepeatForever);
	}

	public boolean ccTouchesBegan(MotionEvent event) {

		return super.ccTouchesBegan(event);
	}

	//触屏事件的优先级高于其他任何动作
	public boolean ccTouchesMoved(MotionEvent event) {

		float x = event.getX();
		float y = event.getY();

		CGPoint point = CGPoint.ccp(x, y);
		CCMoveTo moveTo = CCMoveTo.action(3, point);

		sprite.runAction(moveTo);
		return true;
	}

	public boolean ccTouchesEnded(MotionEvent event) {
		return super.ccTouchesEnded(event);
	}
}
