package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class CheckBoxRowView extends RowView {
	private CheckBox checkBox;

	public CheckBoxRowView(Context context) {
		super(context);
	}

	@Override
	public CheckBox initWidget() {
		checkBox = new CheckBox(getContext());
		// checkBox.setButtonDrawable(R.drawable.setting_view_item_selector);
		checkBox.setChecked(PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean(key, false));
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean(key, isChecked).commit();
			}
		});
		return checkBox;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		if (checkBox != null) {
			checkBox.performClick();
		}
	}

	@Override
	public void addWidgetResource(int resId) {
		checkBox.setButtonDrawable(resId);
	}

}
