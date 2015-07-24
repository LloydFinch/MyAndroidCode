
package com.example.android_day0701_venn_homework.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android_day0701_venn_homework.R;

/**
 * project:com.example.android_day0701_venn_homework.fragment
 * user:VennUser
 * date:2015/7/2
 */
public class MesDetailFragment extends Fragment {
	private static Context context;
	private static String messageContent;
	private static String messageTime;

	public MesDetailFragment() {

	}

	public static void getContext(Context con) {
		context = con;
	}


	public static void getMessage(String mesContent, String mesTime) {
		messageContent = mesContent;
		messageTime = mesTime;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mesdetail, container, false);
		((TextView)view.findViewById(R.id.text_message_content)).setText(messageContent);
		((TextView)view.findViewById(R.id.text_message_time)).setText(messageTime);
		return view;
	}
}