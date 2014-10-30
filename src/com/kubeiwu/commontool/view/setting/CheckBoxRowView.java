package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class CheckBoxRowView extends RowView {
	private CheckBox checkBox;
	private Boolean defaultValue = false;

	public CheckBoxRowView(Context context) {
		super(context);
	}

	@Override
	public CheckBox initWidget() {
		checkBox = new CheckBox(getContext());
		try {
			checkBox.setChecked(TextUtils.isEmpty(preference_key) ? defaultValue : PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(preference_key, defaultValue));
		} catch (Exception e) {
			e.printStackTrace();
		}
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				try {
					if (!TextUtils.isEmpty(preference_key)) {
						PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(preference_key, isChecked).commit();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		return checkBox;
	}

	@Override
	public void onClick(View v) {
		if (checkBox != null) {
			checkBox.performClick();
		}
	}

	@Override
	public void addWidgetResource(int resId) {
		checkBox.setButtonDrawable(resId);
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		if (!TextUtils.isEmpty(this.preference_key) && this.preference_key.equals(key)) {
			try {
				checkBox.setChecked(sharedPreferences.getBoolean(key, defaultValue));
				super.onRowClick();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setValue(Object defaultValue) {
		if (defaultValue instanceof Boolean) {
			this.defaultValue = (Boolean) defaultValue;
		}

	}

}
