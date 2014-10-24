package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

public class DefaultRowView extends RowView {
	private ImageView child;

	public DefaultRowView(Context context) {
		super(context);
	}

	@Override
	public View initWidget() {
		child = new ImageView(getContext());
		// child.setImageResource(R.drawable.arrow_to_right);
		return child;
	}

	public void addWidgetResource(int resId) {
		child.setImageResource(resId);
	}
}
