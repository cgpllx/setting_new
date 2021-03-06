package com.kubeiwu.commontool.view.setting;

import java.lang.reflect.Constructor;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kubeiwu.commontool.view.util.Listener.OnRowViewClickListener;
import com.kubeiwu.commontool.view.util.Para;

public abstract class RowView extends LinearLayout implements OnClickListener, OnSharedPreferenceChangeListener {

	private ImageView mWidgetRowAction_Icon;
	private TextView mWidgetRow_Label;
	protected TextView mWidgetRow_Value;
	private FrameLayout mWidgetRow_Type;
	private int itemId;
	private RowView next = null;
	protected String mKey;
	protected SharedPreferences sharedPreferences;

	public TextView getRowViewTitle() {
		return mWidgetRow_Label;
	}

	/**
	 * Checks whether this Preference has a valid key.
	 * 
	 * @return True if the key exists and is not a blank string, false otherwise.
	 */
	public boolean hasKey() {
		return !TextUtils.isEmpty(mKey);
	}

	public String getKey() {
		return mKey;
	}

	public RowView getNext() {
		return next;
	}

	public boolean hasNext() {
		return next != null;
	}

	private RowView lastRowView = this;

	public CharSequence getTitle() {
		if (mWidgetRow_Label == null) {
			return "";
		}
		return mWidgetRow_Label.getText();
	}

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

	public RowView(Context context) {
		super(context);
		initRowView();
	}

	public void setLastRowView(RowView lastRowView) {
		this.lastRowView = lastRowView;
	}

	private void initRowView() {
		setOrientation(VERTICAL);
		int viewlayout_id = getResources().getIdentifier("setting_view_basic_item", "layout", getContext().getPackageName());
		int mWidgetRowAction_Icon_id = getResources().getIdentifier("mWidgetRowAction_Icon", "id", getContext().getPackageName());
		int mWidgetRow_Label_id = getResources().getIdentifier("mWidgetRow_Label", "id", getContext().getPackageName());
		int mWidgetRow_Value_id = getResources().getIdentifier("mWidgetRow_Value", "id", getContext().getPackageName());
		int mWidgetRow_Type_id = getResources().getIdentifier("mWidgetRow_Type", "id", getContext().getPackageName());

		LayoutInflater.from(getContext()).inflate(viewlayout_id, this);

		mWidgetRowAction_Icon = (ImageView) findViewById(mWidgetRowAction_Icon_id);
		mWidgetRow_Label = (TextView) findViewById(mWidgetRow_Label_id);
		mWidgetRow_Value = (TextView) findViewById(mWidgetRow_Value_id);
		mWidgetRow_Type = (FrameLayout) findViewById(mWidgetRow_Type_id);

		// 初始化Preferences 让子类直接可以用
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());

		int hight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());

		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, hight));

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

	@SuppressWarnings("unchecked")
	public void initRowViewData(Builder<?> rowBuilder) {
		String title = rowBuilder.lable;
		if (TextUtils.isEmpty(title)) {
			mWidgetRow_Label.setVisibility(View.GONE);
		} else {
			mWidgetRow_Label.setText(rowBuilder.lable);
		}
		if (rowBuilder.para != null) {
			this.mKey = rowBuilder.para.key;
			this.para = (Para<Object>) rowBuilder.para;
		}

		setItemId(rowBuilder.itemId);
		setOnClickListener(this);
		mWidgetRow_Type.addView(initWidget());
		addWidgetResource(rowBuilder.resId);
		if (rowBuilder.iconResourceId != 0) {
			mWidgetRowAction_Icon.setImageResource(rowBuilder.iconResourceId);
		} else {
			mWidgetRowAction_Icon.setVisibility(View.GONE);
		}
		mWidgetRow_Value.setVisibility(View.GONE);// 暂时没有用到，先隐藏
		dispatchSetInitialValue();

	}

	public void setRowViewPaddingStart(int paddingstart) {
		if (getChildCount() > 0) {
			getChildAt(0).setPadding(paddingstart, 0, 0, 0);
		}
	}

	public void setRowViewPaddingEnd(int paddingEnd) {
		if (getChildCount() > 0) {
			getChildAt(getChildCount() - 1).setPadding(0, 0, paddingEnd, 0);
		}
	}

	private void dispatchSetInitialValue() {
		if (!sharedPreferences.contains(mKey)) {
			if (this.para != null && this.para.value != null) {
				onSetInitialValue(false, this.para.value);
			}
		} else {
			onSetInitialValue(true, null);
		}
	}

	public abstract View initWidget();

	public abstract void addWidgetResource(int resId);

	@Override
	public void onClick(View v) {
		onRowViewClick();
	}
	public void onRowViewClick(){
		if (listen != null) {
			listen.onRowViewClick(this);
		}
	}

	public void notifyDataChanged() {
		onInitViewData();
	}

	protected void onInitViewData() {
		//
	};

	private OnRowViewClickListener listen;

	public void setOnRowViewClickListener(OnRowViewClickListener listen) {
		this.listen = listen;
	}

	/**
	 * 存储值
	 * 
	 * @param value
	 * @return
	 */
	protected boolean persistInt(int value) {
		if (hasKey()) {
			if (value == getPersistedInt(~value)) {// 判断是不是保存了这个值，如果是，就不再保存（~value按位取反，防止和value相等）
				// It's already there, so the same as persisting
				return true;
			}
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putInt(mKey, value);
			tryCommit(editor, value);
			return true;
		}
		return false;
	}

	private void tryCommit(SharedPreferences.Editor editor, Object value) {
		try {
			handlePara(value);
			editor.apply();
		} catch (AbstractMethodError unused) {
			editor.commit();
		}
		boolean bb=PreferenceManager.getDefaultSharedPreferences(getContext()).getBoolean("dispaly_percentage", true);
		System.out.println("bbbbbb="+bb);

	}

	private void handlePara(Object value) {
		if (para != null) {
			para.value = value;
		}
	}

	protected boolean persistString(String value) {
		if (hasKey()) {
			// Shouldn't store null
			if (value == getPersistedString(null)) {
				// It's already there, so the same as persisting
				return true;
			}

			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString(mKey, value);
			tryCommit(editor, value);
			return true;
		}
		return false;
	}

	// private Object mDefaultValue;
	private Para<Object> para = null;

	/**
	 * 初始化set数据
	 * 
	 * @param restorePersistedValue
	 * @param defaultValue
	 */
	protected void onSetInitialValue(boolean restorePersistedValue, Object defaultValue) {
	}

	protected boolean persistBoolean(boolean value) {
		if (hasKey()) {
			if (value == getPersistedBoolean(!value)) {
				// It's already there, so the same as persisting
				return true;
			}
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putBoolean(mKey, value);
			tryCommit(editor, value);
			System.out.println("保存到xml  mKey="+mKey);
			System.out.println("保存到value="+value);
			return true;
		}
		return false;
	}

	/**
	 * 获取sharedPreferences中保存的 boolean 值
	 * 
	 * @param defaultReturnValue
	 * @return
	 */
	protected boolean getPersistedBoolean(boolean defaultReturnValue) {

		return sharedPreferences.getBoolean(mKey, defaultReturnValue);
	}

	/**
	 * 获取sharedPreferences中保存的 String 值
	 * 
	 * @param defaultReturnValue
	 *            默认
	 * @return
	 */
	protected String getPersistedString(String defaultReturnValue) {

		return sharedPreferences.getString(mKey, defaultReturnValue);
	}

	/**
	 * 获取sharedPreferences中保存的 int 值
	 * 
	 * @param defaultReturnValue
	 *            默认
	 * @return
	 */
	protected int getPersistedInt(int defaultReturnValue) {

		return sharedPreferences.getInt(mKey, defaultReturnValue);
	}

	public static class Builder<T extends RowView> {
		private String lable = "";
		private int iconResourceId;
		private Context context;
		private int resId;
		private Para<?> para;

		public Builder<T> setPara(Para<?> para) {
			this.para = para;
			return this;
		}

		public Builder<T> setResId(int resId) {
			this.resId = resId;
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
	public void changValue(Object object){
		onSetInitialValue(true, object);
	}
}
