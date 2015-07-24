package com.example.android_day0708_homework.adapter;



import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android_day0708_homework.R;
import com.example.android_day0708_homework.asynctask.NewsAsyncTaskDownloadImage;
import com.example.android_day0708_homework.constant.ConstantInterface;
import com.example.android_day0708_homework.entity.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends BaseAdapter
{

	private Context context;
	private List<News> news;
	private ViewHolder viewHolder;

	public NewsAdapter(Context context)
	{
		super();
		this.context = context;
		news = new ArrayList<News>();
	}

	public void addDatas(List<News> newsLIst)
	{
		news.addAll(newsLIst);
	}

	public int getCount()
	{
		return news.size();
	}

	public Object getItem(int position)
	{
		return news.get(position);
	}

	public long getItemId(int position)
	{
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view = convertView == null ? LayoutInflater.from(context).inflate(
				R.layout.adapter_news, null) : convertView;
		viewHolder = (ViewHolder) view.getTag();
		if (viewHolder == null)
		{
			viewHolder = new ViewHolder((ImageView) view.findViewById(R.id.image_news),
					(TextView) view.findViewById(R.id.subject_news),
					(TextView) view.findViewById(R.id.summary_news),
					(TextView) view.findViewById(R.id.time_news));
			view.setTag(viewHolder);
		}

		//TODO: 给imageview设置相应网址标记的
		viewHolder.subjecTextView.setText(news.get(position).getSubject());
		viewHolder.summaryTextView.setText(news.get(position).getSummary());
		viewHolder.changedTextView.setText(news.get(position).getChanged());
		new NewsAsyncTaskDownloadImage(new NewsAsyncTaskDownloadImage.SetImage()
		{
			public void setImageInView(Bitmap bitmap)
			{
				viewHolder.imageView.setImageBitmap(bitmap);
			}
		}).execute(ConstantInterface.baseUrl + news.get(position).getCorver());
		return view;
	}

	class ViewHolder
	{
		ImageView imageView;
		TextView subjecTextView, summaryTextView, changedTextView;

		public ViewHolder(ImageView imageView, TextView subjecTextView,
		                  TextView summaryTextView, TextView changedTextView)
		{
			super();
			this.imageView = imageView;
			this.subjecTextView = subjecTextView;
			this.summaryTextView = summaryTextView;
			this.changedTextView = changedTextView;
		}
	}
}
