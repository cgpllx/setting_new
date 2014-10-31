package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

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

	}

	SparseArray<String> sparseArray = null;

	public DefaultRowView addDataArray(SparseArray<String> sparseArray) {
		this.sparseArray = sparseArray;
		return this;
	}

	public SparseArray<String> getDataArray() {
		return sparseArray;
	}

	public String[] getDisplayValueAsStringArray() {
		if (valueAsStringArray == null) {
			if (sparseArray != null && sparseArray.size() > 0) {
				valueAsStringArray = new String[sparseArray.size()];
				for (int i = 0; i < sparseArray.size(); i++) {
					valueAsStringArray[i] = sparseArray.valueAt(i);
				}
				return valueAsStringArray;
			}
		}
		return valueAsStringArray;
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

	private int restoreValue;// 保存的值

	public boolean saveValue(String value) {
		restoreValue = sparseArray.keyAt(sparseArray.indexOfValue(value));
		return sharedPreferences.edit().putInt(preference_key, restoreValue).commit();
	}

	private String[] valueAsStringArray = null;// 保持单列，提高效率，显示值的数组

	public int getValueIndex() {
		int restoreValue = sharedPreferences.getInt(this.preference_key, 1);
		return sparseArray.indexOfKey(restoreValue);
	}

	private void initValueData() {
		try {
			if (sparseArray != null && sparseArray.size() > 0) {
				value.setText(TextUtils.isEmpty(preference_key) ? defaultValue : //
						sparseArray.get(sharedPreferences.getInt(preference_key, 1), defaultValue));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (!TextUtils.isEmpty(this.preference_key) && this.preference_key.equals(key)) {
			initValueData();
		}
	}

	public DefaultRowView setValue(String defaultValue) {
		this.defaultValue = defaultValue;
		return this;
	}
	@Override
	public void notifyDataChanged() {
		super.notifyDataChanged();
		initValueData();
	}

}
