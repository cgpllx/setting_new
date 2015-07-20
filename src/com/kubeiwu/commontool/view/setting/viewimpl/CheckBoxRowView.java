package com.kubeiwu.commontool.view.setting.viewimpl;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.kubeiwu.commontool.view.setting.RowView;
import com.kubeiwu.commontool.view.util.OnRowClickListener;
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
				System.out.println("isChecked="+isChecked);
				setChecked(isChecked);
			}
		});
		checkBox.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				RowViewClick();
				System.out.println("回调");
				// onRowViewClick();//
				//调用父类的onClick方法。这里调用onRowViewClick也是可以的
				CheckBoxRowView.super.onClick(v);// checkBox被点击时候，模拟item点击
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
		simulationCheckBoxClick();
		System.out.println("回调222222");
		// super.onClick(v);//这里不调用父类点击方法，交给checkBox点击时间调用
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
	}

	private void initCheckBoxData() {
		checkBox.setChecked(mChecked);

	}

	/**
	 * 初始化set的值
	 */
	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		System.out.println(restoreValue);
		System.out.println(defaultValue);
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
