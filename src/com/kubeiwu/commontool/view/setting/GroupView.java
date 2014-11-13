package com.kubeiwu.commontool.view.setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.kubeiwu.commontool.view.util.DisplayOptions;
import com.kubeiwu.commontool.view.util.ItemBgSelectorUtil;
import com.kubeiwu.commontool.view.util.Para;

public class GroupView extends LinearLayout {

	private String mGorupViewTitle;

	public String getTitle() {
		return mGorupViewTitle;
	}

	private GroupView next = null;

	private GroupView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initGroupView();
	}

	/**
	 * RowView 在GroupView中的位置，上 中 下 All(只有一个)
	 * 
	 * @author Administrator
	 * 
	 */
	public interface RowViewPosition {
		int UP = 0, MIDDLE = 1, DOWM = 2, ALL = 3;
	}

	public GroupView getNext() {
		return next;
	}

	public void addGroupViewLastNode(GroupView groupView) {
		lastGroupView.next = groupView;
		lastGroupView = groupView;
	}

	private GroupView lastGroupView = this;

	public boolean hasNext() {
		return next != null;
	}

	private GroupView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	private GroupView(Context context) {
		super(context);
		initGroupView();
	}

	private void initGroupView() {
		setOrientation(VERTICAL);
		setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
		setDividerPadding(2);
		LayoutParams l = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		setPadding(0, 10, 0, 10);
		setLayoutParams(l);
	}

	/**
	 * 增加一组RowView
	 * 
	 * @param rowViewArray
	 *            key 升序 作为RowView在groupView中的位置的循序
	 * @deprecated
	 */
	public void addAllRowView(SparseArray<RowView> rowViewArray) {
		if (mRowViewArray == null || mRowViewArray.size() == 0) {
			mRowViewArray = rowViewArray;
		} else {
			for (int i = 0; i < rowViewArray.size(); i++) {
				RowView linked = this.mRowViewArray.valueAt(i);
				RowView deliverylinked = rowViewArray.valueAt(i);
				if (linked == null) {
					mRowViewArray.put(rowViewArray.keyAt(i), deliverylinked);
				} else {
					linked.addRowViewLastNode(deliverylinked);
				}
			}
		}
	}

	/**
	 * 获取当前groupView的子view
	 * 
	 * @return
	 */
	public SparseArray<RowView> getmRowViewArray() {
		return mRowViewArray;
	}

	/**
	 * 增加单个RowView
	 * 
	 * @param order
	 * @param rowView
	 */
	public void addRowView(int order, RowView rowView) {
		RowView entry = this.mRowViewArray.get(order);
		if (entry == null) {
			this.mRowViewArray.put(order, rowView);
		} else {
			entry.addRowViewLastNode(rowView);
		}
	}

	/**
	 * 增加单个RowView
	 * 
	 * @param order
	 * @param rowView
	 */
	@SuppressLint("NewApi")
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int order, int itemId, String rowTitle, int iconResourceId, String key, int resId, Object defaultValue) {
		RowView entry = this.mRowViewArray.get(order);
		T rowView = new RowView.Builder<T>(getContext()).setItemId(itemId).setIconResourceId(iconResourceId)//
				.setLable(rowTitle)//
				.setKey(key)//
				.setResId(resId)//
				.setDefaultValue(defaultValue)//
				.create(clazz);
		if (entry == null) {
			this.mRowViewArray.put(order, rowView);
		} else {
			entry.addRowViewLastNode(rowView);
		}
		return rowView;
	}

	public <T extends RowView> T addRowViewItem(Class<T> clazz, int order, int itemId, String rowTitle, int iconResourceId, int resId, Para<?> para) {
		RowView entry = this.mRowViewArray.get(order);
		T rowView = new RowView.Builder<T>(getContext()).setItemId(itemId).setIconResourceId(iconResourceId)//
				.setLable(rowTitle)//
				.setKey(para.key)//
				.setResId(resId)//
				.setDefaultValue(para.value)//
				.create(clazz);
		if (entry == null) {
			this.mRowViewArray.put(order, rowView);
		} else {
			entry.addRowViewLastNode(rowView);
		}
		return rowView;
	}

	/**
	 * 增加单个RowView
	 * 
	 * @see 没有order
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int iconResourceId, int resId, Para<?> para) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, iconResourceId, resId, para);
	}

	/**
	 * 增加单个RowView
	 * 
	 * @see 没有order
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int resId, Para<?> para) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, 0, resId, para);
	}

	/**
	 * 增加单个RowView
	 * 
	 * @see 没有order
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int iconResourceId, String key, int resId, Object defaultValue) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, iconResourceId, key, resId, defaultValue);
	}

	/**
	 * 增加单个RowView
	 * 
	 * @see 没有order
	 * @see 没有默认值
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int iconResourceId, String key, int resId) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, iconResourceId, key, resId, null);
	}

	/**
	 * 增加单个RowView
	 * 
	 * @see 1没有order
	 * @see 2没有默认值
	 * @see 3没有icon图标
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, String key, int resId) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, 0, key, resId, null);
	}

	/**
	 * 增加单个RowView
	 * 
	 * @see 1没有order
	 * @see 2没有默认值
	 * @see 3没有icon图标
	 * @see 4没有 item右边图片的选择器，资源
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, String key) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, 0, key, 0, null);
	}

	/**
	 * 增加单个RowView 在0的位置 1没有order 2没有默认值 3没有icon图标 4没有key
	 * 
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int resId) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, 0, null, resId, null);
	}

	private void addView(RowView rowView, DisplayOptions displayOptions) {
		super.addView(rowView);
		rowView.getRowViewTitle().setTextColor(getResources().getColor(displayOptions.getRowTitleColorId()));
		rowView.getRowViewTitle().setTextSize(displayOptions.getRowTitleSizePx());
		rowView.notifyDataChanged();
	}

	public void notifyDataChanged() {
		if (displayOptions == null) {
			displayOptions = DisplayOptions.createsimpleDisplayOptions();
		}
		if (this.mRowViewArray != null && this.mRowViewArray.size() > 0) {
			RowView rowView = null;
			for (int i = 0; i < this.mRowViewArray.size(); i++) {
				rowView = this.mRowViewArray.valueAt(i);
				rowView.setRowViewPaddingStart(displayOptions.getRowPaddingStart());
				rowView.setRowViewPaddingEnd(displayOptions.getRowPaddingEnd());
				addView(rowView, displayOptions);
				while (rowView.hasNext()) {
					rowView = rowView.getNext();
					rowView.setRowViewPaddingStart(displayOptions.getRowPaddingStart());
					rowView.setRowViewPaddingEnd(displayOptions.getRowPaddingEnd());
					addView(rowView, displayOptions);
				}
			}
			afreshRowViewSelector(displayOptions);
		} else {
			setVisibility(View.GONE);
		}
	}

	/**
	 * 重新刷新选择器
	 */
	@SuppressLint("NewApi")
	private void afreshRowViewSelector(DisplayOptions displayOptions) {
		int count = getChildCount();
		if (count <= 1) {
			getChildAt(0).setBackground(creatDrawable(RowViewPosition.ALL, displayOptions));
		} else {
			getChildAt(0).setBackground(creatDrawable(RowViewPosition.UP, displayOptions));
			for (int i = 1; i < count - 1; i++) {
				getChildAt(i).setBackground(creatDrawable(RowViewPosition.MIDDLE, displayOptions));
			}
			getChildAt(count - 1).setBackground(creatDrawable(RowViewPosition.DOWM, displayOptions));
		}
	}

	private DisplayOptions displayOptions;

	public void setDisplayOptions(DisplayOptions displayOptions) {
		this.displayOptions = displayOptions;
	}

	private void initGroupViewData(Builder builder) {
		mGorupViewTitle = builder.gorupViewTitle;
	}

	private Drawable creatDrawable(int rowViewPosition, DisplayOptions displayOptions) {
		ItemBgSelectorUtil itemBgSelectorUtil = new ItemBgSelectorUtil();// displayOptions.getOut_circle_Size(), displayOptions.getLinewidth()
		itemBgSelectorUtil.setLinewidth(displayOptions.getLinewidth());
		itemBgSelectorUtil.setOut_circle_Size(displayOptions.getOut_circle_Size());
		itemBgSelectorUtil.setRowstyle(displayOptions.getRowStyle());
		return itemBgSelectorUtil.createSelector(getContext(), //
				displayOptions.getNormalLineColorId(),//
				displayOptions.getNormalBackgroundColorId(),//
				displayOptions.getPressedLineColorId(), //
				displayOptions.getPressedBackgroundColorId(),//
				rowViewPosition);
	}

	private SparseArray<RowView> mRowViewArray = new SparseArray<RowView>();

	// 在GroupView中添加GroupView
	/**
	 * @deprecated
	 * @param entry
	 */
	public void addGroupView(GroupView entry) {
		SparseArray<RowView> value = entry.getmRowViewArray();// 传进来的值
		addAllRowView(value);
	}

	public static class Builder {
		private String gorupViewTitle;
		private Context context;

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setGorupViewTitle(String gorupViewTitle) {
			this.gorupViewTitle = gorupViewTitle;
			return this;
		}

		public GroupView create() {
			final GroupView groupView = new GroupView(context);
			groupView.initGroupViewData(this);
			return groupView;
		}
	}

}
