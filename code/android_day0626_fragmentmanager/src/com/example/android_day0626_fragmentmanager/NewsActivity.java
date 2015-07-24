package com.example.android_day0626_fragmentmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;
import com.example.android_day0626_fragmentmanager.fragment.NewsFacFragment;
import com.example.android_day0626_fragmentmanager.fragment.NewsFragment;
import com.example.android_day0626_fragmentmanager.fragment.NewsMovieFragment;
import com.example.android_day0626_fragmentmanager.fragment.NewsSelfFragment;

/**
 * project:com.example.android_day0626_fragmentmanager
 * user:VennUser
 * date:2015/6/26
 */
//模拟网易新闻的切换内容,点击底部按钮切换内容
//程序启动默认显示首页
//点击单选按钮触发切换Fragment替换事件
public class NewsActivity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener
{
	private RadioGroup radioGroup;
	private NewsFacFragment newsFacFragment;
	private NewsFragment newsFragment;
	private NewsMovieFragment newsMovieFragment;
	private NewsSelfFragment newsSelfFragment;
	private FragmentManager manager;

	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);

		NewsFragment newsFragment = new NewsFragment();
		manager = getSupportFragmentManager();
		FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.news_content, newsFragment);
		transaction.commit();

		radioGroup = (RadioGroup) this.findViewById(R.id.news_tab_bar);
		if (radioGroup != null)
		{
			radioGroup.setOnCheckedChangeListener(this);
		}
	}

	//好思想：使用单例模式,用才创建，下次使用直接使用
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		FragmentTransaction transaction = manager.beginTransaction();
		switch (checkedId)
		{
			case R.id.news_tab_home:
				newsFragment = newsFragment == null ? new NewsFragment() : newsFragment;
				transaction.replace(R.id.news_content, newsFragment);
				break;
			case R.id.news_tab_guanzhu:
				newsFacFragment = newsFacFragment == null ? new NewsFacFragment() : newsFacFragment;
				transaction.replace(R.id.news_content, newsFacFragment);
				break;
			case R.id.news_tab_video:
				newsMovieFragment = newsMovieFragment == null ? new NewsMovieFragment() : newsMovieFragment;
				transaction.replace(R.id.news_content, newsMovieFragment);
				break;
			case R.id.news_tab_myself:
				newsSelfFragment = newsSelfFragment == null ? new NewsSelfFragment() : newsSelfFragment;
				transaction.replace(R.id.news_content, newsSelfFragment);
				break;
		}
		//记录Fragment回退栈信息
		//transaction.addToBackStack(null);
		transaction.commit();
	}

	public void btnR_onclick(View view)
	{
		Intent intent = new Intent(this, RemoveActivity.class);
		startActivity(intent);
	}
}