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
	private String currentValue = "";// 当前值
	private int restoreValue;// 保存的值

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

	/**
	 * 增加数据集合
	 * 
	 * @param sparseArray
	 *            数据 key为要保存的值 value是要显示的值
	 * @param defaultValue
	 *            默认值
	 * @return
	 */
	public DefaultRowView addDataArray(SparseArray<String> sparseArray, int defaultValue) {
		this.sparseArray = sparseArray;
		currentValue = sparseArray.get(defaultValue, "");
		restoreValue = defaultValue;
		return this;
	}

	public DefaultRowView addDataArray(SparseArray<String> sparseArray) {
		return addDataArray(sparseArray, 0);
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

	/**
	 * 保存值
	 * 
	 * @param index
	 *            位置
	 * @return 成功 or 失败
	 */
	public boolean saveValue(final int index) {
		restoreValue = sparseArray.keyAt(index);
		new Thread() {
			public void run() {
				currentValue = sparseArray.get(restoreValue, currentValue);
				sharedPreferences.edit().putInt(preference_key, restoreValue).commit();
			};
		}.start();
		return false;
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

	/**
	 * 保存值
	 * 
	 * @param value
	 *            显示的值
	 * @return
	 */
	public boolean saveValue(String value) {
		restoreValue = sparseArray.keyAt(sparseArray.indexOfValue(value));
		return sharedPreferences.edit().putInt(preference_key, restoreValue).commit();
	}

	private String[] valueAsStringArray = null;// 保持单列，提高效率，显示值的数组

	/**
	 * 获取位置
	 * 
	 * @return 返回当前值在集合中的位置 index
	 */
	public int getValueIndex() {
		int restoreValue = sharedPreferences.getInt(this.preference_key, 1);
		if (sparseArray != null) {
			return sparseArray.indexOfKey(restoreValue);
		}
		return 0;
	}

	/**
	 * 获取储存的值
	 * 
	 * @return
	 */
	public int getRestoreValue() {
		return restoreValue;
	}

	private void initValueData() {
		try {
			if (sparseArray != null && sparseArray.size() > 0) {
				restoreValue = sharedPreferences.getInt(preference_key, 1);
				currentValue = sparseArray.get(restoreValue, currentValue);
				value.setText(currentValue);
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
		this.currentValue = defaultValue;
		return this;
	}

	@Override
	public void notifyDataChanged() {
		super.notifyDataChanged();
		initValueData();
	}

}
