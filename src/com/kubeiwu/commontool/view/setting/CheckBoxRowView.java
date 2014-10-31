package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

public class CheckBoxRowView extends RowView {
	private CheckBox checkBox;
	private Boolean currentValue = false;// 当前值

	public CheckBoxRowView(Context context) {
		super(context);
	}

	@Override
	public CheckBox initWidget() {
		checkBox = new CheckBox(getContext());
		return checkBox;
	}

	public Boolean getCurrentValue() {
		return currentValue;
	}

	@Override
	public void addWidgetResource(int resId) {
		checkBox.setButtonDrawable(resId);// 设置checkBox的选择器
	}

	private OnRowClickListener<CheckBoxRowView> listen;

	public CheckBoxRowView setOnRowClickListener(OnRowClickListener<CheckBoxRowView> listen) {
		this.listen = listen;
		return this;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		simulationCheckBoxClick();
		RowViewClick();
	}

	// 模拟CheckBox 点击
	public void simulationCheckBoxClick() {
		if (checkBox != null) {
			checkBox.performClick();
		}
	}

	// 回调
	private void RowViewClick() {
		if (listen != null) {
			listen.onRowClick(this, RowViewActionEnum.My_POSTS);
		}
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (!TextUtils.isEmpty(this.preference_key) && this.preference_key.equals(key)) {
			try {
				  currentValue = sharedPreferences.getBoolean(key, currentValue);
				if (checkBox.isChecked() != currentValue) {
					checkBox.setChecked(currentValue);
				}
				RowViewClick();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public CheckBoxRowView setValue(Boolean defaultValue) {
		this.currentValue = defaultValue;
		return this;
	}

	@Override
	public void notifyDataChanged() {
		super.notifyDataChanged();
		checkBox.setChecked(TextUtils.isEmpty(preference_key) ? currentValue : sharedPreferences.getBoolean(preference_key, currentValue));
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				try {
					currentValue=isChecked;
					if (!TextUtils.isEmpty(preference_key)) {
						sharedPreferences.edit().putBoolean(preference_key, isChecked).commit();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
