package com.example.android_day0708_homework.asynctask;

import java.io.IOException;
import java.net.MalformedURLException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.example.android_day0708_homework.tool.NewsHttpUtil;

public class NewsAsyncTaskDownloadImage extends AsyncTask<String, Void, Bitmap> {
	private SetImage setImage;

	public NewsAsyncTaskDownloadImage(SetImage setImage) {
		super();
		this.setImage = setImage;
	}

	protected Bitmap doInBackground(String... params) {
		try {
			byte[] data = NewsHttpUtil.getDatas(params[0]);
			return BitmapFactory.decodeByteArray(data, 0, data.length);
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		setImage.setImageInView(result);
	}

	public interface SetImage {
		void setImageInView(Bitmap bitmap);
	}
}
