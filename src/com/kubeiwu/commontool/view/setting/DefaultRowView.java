package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

/**
 * 点击的item
 * 
 * @author aaa
 *
 */
public class DefaultRowView extends RowView {
	private ImageView child;
	private TextView value;

	public DefaultRowView(Context context) {
		super(context);
	}

	@Override
	public View initWidget() {
		ininImageView();
		initTextView();
		LinearLayout layout = new LinearLayout(getContext());
		layout.setOrientation(HORIZONTAL);
		layout.setGravity(Gravity.CENTER);
		layout.addView(value);
		layout.addView(child);
		return layout;
	}

	@Override
	public void addWidgetResource(int resId) {
		child.setImageResource(resId);
	}

	private void ininImageView() {
		child = new ImageView(getContext());
		child.setPadding(1, 0, 0, 0);
	}

	private void initTextView() {
		value = new TextView(getContext());
		value.setPadding(10, 0, 10, 0);
		value.setSingleLine(true);
		value.setTextColor(getResources().getColor(android.R.color.darker_gray));
		// TypedValue.applyDimension(TypedValue.TYPE_DIMENSION, value, get);
		value.setTextSize(17);

	}

	private OnRowClickListener<DefaultRowView> listen;

	public DefaultRowView setOnRowClickListener(OnRowClickListener<DefaultRowView> listen) {
		this.listen = listen;
		return this;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (listen != null) {
			listen.onRowClick(this, RowViewActionEnum.My_POSTS);
		}
	}

	@Override
	protected void onInitViewData() {
		super.onInitViewData();
	}

	@Override
	protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
		super.onSetInitialValue(restorePersistedValue, defaultValue);
		value.setText(defaultValue.toString());// 这里回调回来设置的默认值
	}

}
