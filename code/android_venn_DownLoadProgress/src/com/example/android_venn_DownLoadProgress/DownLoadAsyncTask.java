package com.example.android_venn_DownLoadProgress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.*;
import android.os.AsyncTask;
import android.widget.ImageView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.security.cert.CRL;

/**
 * project:com.example.android_venn_DownLoadProgress
 * user:VennUser
 * date:2015/6/24
 */
public class DownLoadAsyncTask extends AsyncTask<String, Integer, Bitmap>
{
	private Context context;
	private ImageView imageView;
	private ProgressDialog progressDialog;
	private long total;

	public DownLoadAsyncTask(Context context, ImageView imageView)
	{
		this.context = context;
		this.imageView = imageView;
		progressDialog = new ProgressDialog(context);
	}


	protected void onPreExecute()
	{
		super.onPreExecute();
		progressDialog.setTitle("下载提示");
		progressDialog.setMessage("正在下载....");
		progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialog.setButton(ProgressDialog.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.dismiss();
			}
		});
		progressDialog.show();
	}

	protected Bitmap doInBackground(String... params)
	{
		Bitmap bitmap = null;
		HttpGet get = new HttpGet(params[0]);
		HttpClient client = new DefaultHttpClient();
		HttpResponse response = null;
		try
		{
			response = client.execute(get);
			int current_progress = 0;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			if (response.getStatusLine().getStatusCode() == 200)
			{
				InputStream inputStream = response.getEntity().getContent();
				InputStream inputStream2 = inputStream;
				//bitmap = BitmapFactory.decodeStream(inputStream);
				total = response.getEntity().getContentLength();
				int length = 0;
				byte[] data = new byte[1024];
				while ((length = inputStream.read(data)) != -1)
				{
					byteArrayOutputStream.write(data, 0, data.length);
					publishProgress(current_progress += length);
					try
					{
						Thread.sleep(300);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				byte[] bytes = byteArrayOutputStream.toByteArray();
				//图片返回null的解决方法，待验证
//				YuvImage yuvImage = new YuvImage(bytes, ImageFormat.NV21, 120, 120, null);
//				yuvImage.compressToJpeg(new Rect(0, 0, 120, 120), 80, byteArrayOutputStream);
//				byte[] image = byteArrayOutputStream.toByteArray();
				//图片过大导致内存溢出的解决方法，未成功
//				BitmapFactory.Options options = new BitmapFactory.Options();
//				options.inSampleSize = 2;
//				options.inTempStorage=new byte[1024*1024*4];
				//图片内存过大，再次发送了请求
				bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
				bitmap = BitmapFactory.decodeStream(client.execute(get).getEntity().getContent());
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return bitmap;
	}

	protected void onProgressUpdate(Integer... values)
	{
		progressDialog.setMax((int) total);
		int length = values[0].intValue();
		progressDialog.setProgress(length);
		if (length == total)
		{
			progressDialog.setMessage("下载完毕");
		}
	}

	protected void onPostExecute(Bitmap bitmap)
	{
		imageView.setImageBitmap(bitmap);
	}
}
