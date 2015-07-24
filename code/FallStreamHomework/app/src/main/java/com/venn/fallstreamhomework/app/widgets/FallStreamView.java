package com.venn.fallstreamhomework.app.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.venn.fallstreamhomework.app.R;

import java.io.InterruptedIOException;
import java.security.Policy;

/**
 * Created by VennUser on 2015/7/20.
 */
public class FallStreamView extends ScrollView {

	private int columns, width;
	private int spacing;

	public void setColumns(int columns, int width, Context context) {
		this.columns = columns;
		this.width = width;
		init(context, null);
	}

	public FallStreamView(Context context) {
		super(context);
	}

	public FallStreamView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {

		spacing = getSpacing();

		//Because a ScrollView can host only one direct child
		//so you should remove all view before you add other
		//view in it
		this.removeAllViews();

		LinearLayout linearLayout = new LinearLayout(context);
		linearLayout.setOrientation(LinearLayout.HORIZONTAL);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);

		linearLayout.setLayoutParams(params);

		addSubView(linearLayout, context);

		this.addView(linearLayout);
	}

	private void addSubView(LinearLayout linearLayout, Context context) {
		for (int i = 0; i < columns; i++) {

			LinearLayout subLinearLayout = new LinearLayout(context);
			subLinearLayout.setOrientation(LinearLayout.VERTICAL);
			//subLinearLayout.setDividerPadding(10);
			//subLinearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

			ImageView imageView = new ImageView(context);
			imageView.setImageResource(R.mipmap.ic_launcher);
			subLinearLayout.addView(imageView);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,
					1);

			subLinearLayout.setLayoutParams(params);

			LinearLayout spaceLinearLayout = new LinearLayout(context);
			LinearLayout.LayoutParams sParams = new LinearLayout.LayoutParams(spacing, ViewGroup.LayoutParams
					.MATCH_PARENT);
			spaceLinearLayout.setLayoutParams(sParams);
			spaceLinearLayout.setBackgroundColor(Color.TRANSPARENT);

			ImageView spaceImageView = new ImageView(context);
			spaceImageView.setImageResource(R.mipmap.spac);
			spaceLinearLayout.addView(spaceImageView);


			switch (i % 2) {
				case 0:
					subLinearLayout.setBackgroundColor(Color.RED);
					break;
				case 1:
					subLinearLayout.setBackgroundColor(Color.YELLOW);
					break;
			}
			linearLayout.addView(subLinearLayout);
			if (i < columns - 1) {
				linearLayout.addView(spaceLinearLayout);
			}
		}
	}

	private int getSpacing() {
		int pad = width / (columns - 1);
		return pad;
	}
}
