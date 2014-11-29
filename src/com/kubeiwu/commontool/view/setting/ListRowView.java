package com.kubeiwu.commontool.view.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.Para;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

public class ListRowView extends DialogRowView {
	private ImageView child_ImageView;
	private TextView value_TextView;
	private CharSequence[] mEntries;// 要显示的值
	private int[] mEntryValues;// 真正要保存的值
	private int mValue;// 当前 的值
	private int mClickedDialogEntryIndex;

	public ListRowView(Context context) {
		super(context);
	}

	/**
	 * 初始化View
	 */
	@Override
	public View initWidget() {
		ininImageView();
		initTextView();
		LinearLayout layout = new LinearLayout(getContext());
		layout.setOrientation(HORIZONTAL);
		layout.setGravity(Gravity.CENTER);
		layout.addView(value_TextView);
		layout.addView(child_ImageView);
		return layout;
	}

	private void ininImageView() {
		child_ImageView = new ImageView(getContext());
		child_ImageView.setPadding(1, 0, 0, 0);
	}

	private void initTextView() {
		value_TextView = new TextView(getContext());
		value_TextView.setPadding(10, 0, 10, 0);
		value_TextView.setSingleLine(true);
		value_TextView.setTextColor(getResources().getColor(android.R.color.darker_gray));
		// TypedValue.applyDimension(TypedValue.TYPE_DIMENSION, value, get);
		value_TextView.setTextSize(17);
	}

	// 右边的图片资源
	public void addWidgetResource(int resId) {
		child_ImageView.setImageResource(resId);
	}

	/**
	 * 根据值 查找位置index
	 * 
	 * @param value
	 * @return
	 */
	public int findIndexOfValue(int value) {
		if (mEntryValues != null) {
			for (int i = mEntryValues.length - 1; i >= 0; i--) {
				if (mEntryValues[i] == value) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * 获取当前显示的值
	 * 
	 * @return
	 */
	public CharSequence getEntry() {
		int index = getValueIndex();
		return index >= 0 && mEntries != null ? mEntries[index] : null;
	}

	/**
	 * 获取当前值的index
	 * 
	 * @return
	 */
	private int getValueIndex() {
		return findIndexOfValue(mValue);
	}

	/**
	 * 设置真正要保存的值的数组
	 * 
	 * @param entryValues
	 */
	public ListRowView setEntryValues(int... entryValues) {
		mEntryValues = entryValues;
		return this;
	}

	/**
	 * Sets the value of the key. This should be one of the entries in
	 * {@link #getEntryValues()}.
	 * 
	 * @param value
	 *            The value to set for the key.
	 */
	public void setValue(int value) {
		if (mValue != value) {
			mValue = value;
			persistInt(value);
			if (para != null) {
				para.value = mValue;
			}
		}
	}

	public int[] getEntryValues() {
		return mEntryValues;
	}

	public ListRowView setEntries(CharSequence... entries) {
		mEntries = entries;
		return this;
	}

	public CharSequence[] getEntries() {
		return mEntries;
	}

	@Override
	protected void onPrepareDialogBuilder(AlertDialog.Builder builder) {
		super.onPrepareDialogBuilder(builder);
		if (mEntries == null || mEntryValues == null) {
			throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
		}

		mClickedDialogEntryIndex = getValueIndex();
		builder.setSingleChoiceItems(mEntries, mClickedDialogEntryIndex, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				mClickedDialogEntryIndex = which;

				/*
				 * Clicking on an item simulates the positive button click, and
				 * dismisses the dialog.
				 */
				ListRowView.this.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
				dialog.dismiss();
			}
		});
		// 不要确定按钮
		builder.setPositiveButton(null, null);
	}

	/**
	 * dialog 关闭后执行
	 */
	protected void onDialogClosed(boolean positiveResult) {
		super.onDialogClosed(positiveResult);
		if (positiveResult && mClickedDialogEntryIndex >= 0 && mEntryValues != null) {
			int value = mEntryValues[mClickedDialogEntryIndex];
			setValue(value);
			initValueData();
			if (listen != null) {// 值改变后执行
				listen.onRowClick(this, RowViewActionEnum.My_POSTS);
			}
		}
	}

	private OnRowClickListener<ListRowView> listen;

	public ListRowView setOnRowClickListener(OnRowClickListener<ListRowView> listen) {
		this.listen = listen;
		return this;
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		showDialog();
	}

	private void initValueData() {
		value_TextView.setText(getEntry());
	}

	private Para<Integer> para;

	public void setPara(Para<Integer> para) {
		this.para = para;
	}

	/**
	 * 初始化set的值
	 */
	@Override
	protected void onSetInitialValue(boolean restoreValue, Object defaultValue) {
		setValue(restoreValue ? getPersistedInt(mValue) : (Integer) defaultValue);
	}

	/**
	 * 在这里初始化view显示的数据
	 */
	@Override
	protected void onInitViewData() {
		super.onInitViewData();
		initValueData();
	}
}
