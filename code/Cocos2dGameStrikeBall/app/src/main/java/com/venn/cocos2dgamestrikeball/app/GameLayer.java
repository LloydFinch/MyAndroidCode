package com.venn.cocos2dgamestrikeball.app;

;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.MotionEvent;
import org.cocos2d.actions.interval.*;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * Created by VennUser on 2015/7/22.
 */
public class GameLayer extends CCLayer {

	private CCSprite sprite;

	public GameLayer(Bitmap bitmap) {

		//设置可以触屏
		this.setIsTouchEnabled(true);

		sprite = CCSprite.sprite("balloon.png");
		//sprite = CCSprite.sprite(bitmap);
		CGPoint point = CGPoint.ccp(250, 100);

		sprite.setPosition(point);
		this.addChild(sprite);

		CGPoint targetPoint = CGPoint.ccp(150, 1000);

		CCRotateBy rotateBy = CCRotateBy.action(1, 360);
		CCRepeat repeat = CCRepeat.action(rotateBy, 10);

		CCMoveBy moveBy = CCMoveBy.action(5, targetPoint);

		CCSpawn ccSpawn = CCSpawn.actions(moveBy, repeat);

		//精灵执行动画
		sprite.runAction(ccSpawn);

	}

	//点击气球就移动到指定位置
	public boolean ccTouchesMoved(MotionEvent event) {

		float x = -event.getX();
		float y = -event.getY();
		CGPoint targetPoint = CGPoint.ccp(x, y);

		CCMoveTo moveTo = CCMoveTo.action(1, targetPoint);

		sprite.runAction(moveTo);

		return true;
	}
}
