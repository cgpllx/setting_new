package com.kubeiwu.commontool.view.setting.viewimpl;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.kubeiwu.commontool.view.setting.RowView;
import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.Para;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

public class CheckBoxRowView extends RowView {
	private CheckBox checkBox;
	private Boolean mChecked = false;// 当前值

	public CheckBoxRowView(Context context) {
		super(context);
	}

	@Override
	public CheckBox initWidget() {
		checkBox = new CheckBox(getContext());
		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setChecked(isChecked);
			}
		});
		checkBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RowViewClick();
			}
		});
		return checkBox;
	}

	public Boolean getCurrentValue() {
		return mChecked;
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
	private Para<Boolean> para;

	public void setPara(Para<Boolean> para) {
		this.para = para;
	}

	/**
	 * Returns the checked state.
	 * 
	 * @return The checked state.
	 */
	public boolean isChecked() {
		return mChecked;
	}

	/**
	 * 
	 * @param checked
	 *            The checked state.
	 */
	public void setChecked(boolean checked) {
		if (mChecked != checked) {
			mChecked = checked;
			persistBoolean(checked);
		}
		if (para != null) {
			para.value = mChecked;
		}
	}

	private void initCheckBoxData() {
		checkBox.setChecked(mChecked);

	}

	/**
	 * 初始化set的值
	 */
	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		setChecked(restoreValue ? getPersistedBoolean(mChecked) : (Boolean) defaultValue);
	}

	/**
	 * 在这里初始化view显示的数据
	 */
	@Override
	protected void onInitViewData() {
		super.onInitViewData();
		initCheckBoxData();
	}

}
