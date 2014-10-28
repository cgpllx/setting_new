package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DefaultRowView extends RowView {
	private ImageView child;
	private TextView value;
	private String defaultValue = "";

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
		try {
			value.setText(TextUtils.isEmpty(key) ? defaultValue : PreferenceManager.getDefaultSharedPreferences(getContext()).getString(key, defaultValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (TextUtils.isEmpty(this.key) &&this.key.equals(key)) {
			try {
				value.setText(TextUtils.isEmpty(key) ? defaultValue : sharedPreferences.getString(key, defaultValue));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setValue(Object defaultValue) {
		this.defaultValue=defaultValue.toString();
	}

}
