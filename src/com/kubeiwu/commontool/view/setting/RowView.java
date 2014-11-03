package com.kubeiwu.commontool.view.setting;

import java.lang.reflect.Constructor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public abstract class RowView extends LinearLayout implements OnClickListener, OnSharedPreferenceChangeListener {

	private ImageView mWidgetRowAction_Icon;
	private TextView mWidgetRow_Label;
	protected TextView mWidgetRow_Value;
	private FrameLayout mWidgetRow_Type;
	private int itemId;
	private RowView next = null;
	protected String preference_key;
	protected SharedPreferences sharedPreferences;

	public TextView getRowViewTitle() {
		return mWidgetRow_Label;
	}

	public String getKey() {
		return preference_key;
	}
	public RowView getNext() {
		return next;
	}  
	public boolean hasNext() {
		return next != null;
	}
	private RowView lastRowView = this;

	public void addRowViewLastNode(RowView rowView) {
		lastRowView.next = rowView;
		lastRowView = rowView;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	protected RowView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initRowView();
	}

	protected RowView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	protected RowView(Context context) {
		super(context);
		initRowView();
	}


	public void setLastRowView(RowView lastRowView) {
		this.lastRowView = lastRowView;
	}


	private void initRowView() {

		int viewlayout_id = getResources().getIdentifier("setting_view_basic_item", "layout", getContext().getPackageName());
		int mWidgetRowAction_Icon_id = getResources().getIdentifier("mWidgetRowAction_Icon", "id", getContext().getPackageName());
		int mWidgetRow_Label_id = getResources().getIdentifier("mWidgetRow_Label", "id", getContext().getPackageName());
		int mWidgetRow_Value_id = getResources().getIdentifier("mWidgetRow_Value", "id", getContext().getPackageName());
		int mWidgetRow_Type_id = getResources().getIdentifier("mWidgetRow_Type", "id", getContext().getPackageName());

		// int mWidgetRow_righ_Common_arrow_id = getResources().getIdentifier("mWidgetRow_righ_Common_arrow", "id", getContext().getPackageName());

		LayoutInflater.from(getContext()).inflate(viewlayout_id, this);
		mWidgetRowAction_Icon = (ImageView) findViewById(mWidgetRowAction_Icon_id);
		mWidgetRow_Label = (TextView) findViewById(mWidgetRow_Label_id);
		mWidgetRow_Value = (TextView) findViewById(mWidgetRow_Value_id);
		mWidgetRow_Type = (FrameLayout) findViewById(mWidgetRow_Type_id);

		// mWidgetRow_righ_Common_arrow = (ImageView) findViewById(mWidgetRow_righ_Common_arrow_id);

		// LayoutInflater.from(getContext()).inflate(R.layout.setting_view_basic_item, this);
		// mWidgetRowAction_Icon = (ImageView) findViewById(R.id.mWidgetRowAction_Icon);
		// mWidgetRow_Label = (TextView) findViewById(R.id.mWidgetRow_Label);
		// mWidgetRow_Value = (TextView) findViewById(R.id.mWidgetRow_Value);
		// mWidgetRow_righ_Common_arrow = (ImageView) findViewById(R.id.mWidgetRow_righ_Common_arrow);
		//初始化Preferences 让子类直接可以用
		sharedPreferences=PreferenceManager.getDefaultSharedPreferences(getContext());

	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		PreferenceManager.getDefaultSharedPreferences(getContext()).registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		PreferenceManager.getDefaultSharedPreferences(getContext()).unregisterOnSharedPreferenceChangeListener(this);
	}


	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		// TODO:让子类根据自己需求重写
	}

	// @Override
	// public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
	// // setValue(PreferenceManager.getDefaultSharedPreferences(getContext()).get)
	// }


	public void initRowViewData(Builder<?> rowBuilder) {
		mWidgetRow_Label.setText(rowBuilder.lable);
		// mWidgetRow_Label.setTextColor(Color.parseColor("#777777"));

		this.preference_key = rowBuilder.key;
		setItemId(rowBuilder.itemId);
		setOnClickListener(this);
		// if (rowBuilder.action != null) {
		// mWidgetRow_righ_Common_arrow.setVisibility(View.VISIBLE);// 可以点击时候显示箭头
		// } else {
		// mWidgetRow_righ_Common_arrow.setVisibility(View.GONE);// 不可以点击时候隐藏箭头
		// }
		// initRowViewType(mRowViewType);
		mWidgetRow_Type.addView(initWidget());
		addWidgetResource(rowBuilder.resId);
		if (rowBuilder.iconResourceId != 0) {
			mWidgetRowAction_Icon.setImageResource(rowBuilder.iconResourceId);
		} else {
			mWidgetRowAction_Icon.setVisibility(View.GONE);
		}
		// if (!TextUtils.isEmpty(rowBuilder.defaultValue)) {
		// mWidgetRow_Value.setText(rowBuilder.defaultValue);
		// } else {
		mWidgetRow_Value.setVisibility(View.GONE);
		// }

	}

	public abstract View initWidget();

	public abstract void addWidgetResource(int resId);

	@Override
	public void onClick(View v) {
		// onRowClick();
	}

	// public void onRowClick() {
	// if (rowBuilder.getListener() != null) {
	// // rowBuilder.getListener().onRowClick(t, rowBuilder.action);
	// }
	// }

	@SuppressLint("NewApi")
	public void notifyDataChanged() {

//		switch (mRowViewPosition) {
//		case RowView.RowViewPosition.UP:
//			setBackground(upSelector);
//			break;
//		case RowView.RowViewPosition.MIDDLE:
//			setBackground(middleSelector);
//			break;
//		case RowView.RowViewPosition.DOWM:
//			setBackground(downSelector);
//			break;
//		case RowView.RowViewPosition.ALL:
//			setBackground(allSelector);
//			break;
//
//		}
	}

	public static class Builder<T extends RowView> {
		private String lable = "";
		private int iconResourceId;
		private Context context;
		private String key;
		private int resId;

	 

		public Builder<T> setResId(int resId) {
			this.resId = resId;
			return this;
		}

		public Builder<T> setKey(String key) {
			this.key = key;
			return this;
		}

		public int getItemId() {
			return itemId;
		}

		public Builder<T> setItemId(int itemId) {
			this.itemId = itemId;
			return this;
		}

		private int itemId;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder<T> setLable(String lable) {
			this.lable = lable;
			return this;
		}

		public Builder<T> setIconResourceId(int iconResourceId) {
			this.iconResourceId = iconResourceId;
			return this;
		}

		public T create(Class<T> clazz) {
			try {
				Constructor<T> c = clazz.getConstructor(Context.class);
				final T rowView = c.newInstance(context);
				rowView.initRowViewData(this);
				return rowView;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
