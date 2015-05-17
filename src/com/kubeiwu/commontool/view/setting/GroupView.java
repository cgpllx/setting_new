package com.kubeiwu.commontool.view.setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.kubeiwu.commontool.view.util.ApiCompatibleUtil;
import com.kubeiwu.commontool.view.util.DisplayOptions;
import com.kubeiwu.commontool.view.util.ItemBgSelectorUtil;
import com.kubeiwu.commontool.view.util.Listener.OnGroupViewItemClickListener;
import com.kubeiwu.commontool.view.util.Listener.OnRowViewClickListener;
import com.kubeiwu.commontool.view.util.Para;

public class GroupView extends LinearLayout implements OnRowViewClickListener {

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
	 * 获取当前groupView的子view
	 * 
	 * @return
	 */
	public SparseArray<RowView> getmRowViewArray() {

		return mRowViewArray;
	}

	@Override
	public void setDividerDrawable(Drawable divider) {
		// TODO Auto-generated method stub
		super.setDividerDrawable(divider);
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
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int order, int itemId, String rowTitle, int iconResourceId, int resId, Para<?> para) {
		RowView entry = this.mRowViewArray.get(order);
		T rowView = new RowView.Builder<T>(getContext()).setItemId(itemId).setIconResourceId(iconResourceId)//
				.setLable(rowTitle)//
				// .setKey(para.key)//
				.setResId(resId)//
				// .setDefaultValue(para.value)//
				.setPara(para)//
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
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int resId) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, 0, resId, null);
	}

	/**
	 * 增加单个RowView
	 * 
	 * @see 没有order
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, 0, 0, null);
	}

	private OnGroupViewItemClickListener mOnGroupViewItemClickListener;

	public void setOnItemClickListener(OnGroupViewItemClickListener listener) {
		mOnGroupViewItemClickListener = listener;
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

	private void addView(RowView rowView, DisplayOptions displayOptions) {
		super.addView(rowView);
		rowView.getRowViewTitle().setTextColor(getResources().getColor(displayOptions.getRowTitleColorId()));
		rowView.getRowViewTitle().setTextSize(displayOptions.getRowTitleSizePx());
		rowView.notifyDataChanged();
		rowView.setOnRowViewClickListener(this);

	}

	public void notifyDataChanged() {
		if (displayOptions == null) {
			displayOptions = DisplayOptions.createsimpleDisplayOptions();
		}
		int dividerResId = displayOptions.getDividerResId();
		if (dividerResId != 0) {// 设置当前GroupView的divider
			Drawable drawable = getResources().getDrawable(dividerResId);
			if (drawable != null) {
				setDividerDrawable(drawable);
				setShowDividers(displayOptions.getShowDividers());
			}
		}
		int dividerPadding = displayOptions.getDividerPadding();
		if (dividerPadding != 0) {
			setDividerPadding(dividerPadding);
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
			ApiCompatibleUtil.setViewBackground(getChildAt(0), creatDrawable(RowViewPosition.ALL, displayOptions));
		} else {
			ApiCompatibleUtil.setViewBackground(getChildAt(0), creatDrawable(RowViewPosition.UP, displayOptions));
			for (int i = 1; i < count - 1; i++) {
				ApiCompatibleUtil.setViewBackground(getChildAt(i), creatDrawable(RowViewPosition.MIDDLE, displayOptions));
			}
			ApiCompatibleUtil.setViewBackground(getChildAt(count - 1), creatDrawable(RowViewPosition.DOWM, displayOptions));
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

	@Override
	public void onRowViewClick(RowView rowView) {
		if (mOnGroupViewItemClickListener != null) {
			mOnGroupViewItemClickListener.onItemClick(GroupView.this, rowView);
		}
	}
}
