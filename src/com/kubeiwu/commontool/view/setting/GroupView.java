package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.kubeiwu.commontool.view.setting.RowView.RowViewPosition;
import com.kubeiwu.commontool.view.util.DisplayRowViewOptions;
import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

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
	public RowView addRowViewItem(Class<? extends RowView> clazz, int order, int itemId, String rowTitle, int iconResourceId, String key, int resId, DisplayRowViewOptions selectorPara, OnRowClickListener rowClickListener) {
		RowView entry = this.mRowViewArray.get(order);
		RowView rowView = new RowView.Builder(getContext(), clazz).setItemId(itemId).setIconResourceId(iconResourceId)//
				.setLable(rowTitle).setAction(RowViewActionEnum.My_POSTS).setListener(rowClickListener)//
				.setSelectorPara(selectorPara).setKey(key).setResId(resId).create();
		if (entry == null) {
			this.mRowViewArray.put(order, rowView);
		} else {
			entry.addRowViewLastNode(rowView);
		}
		return rowView;
	}

	/**
	 * 增加单个RowView 在0的位置
	 * 
	 * @param rowView
	 */
	public RowView addRowViewItem(Class<? extends RowView> clazz, int itemId, String rowTitle, int iconResourceId, String key, int resId, DisplayRowViewOptions selectorPara, OnRowClickListener rowClickListener) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, iconResourceId, key, resId, selectorPara, rowClickListener);
	}

	public void notifyDataChanged() {
		if (this.mRowViewArray != null && this.mRowViewArray.size() > 0) {
			RowView rowView = null;
			for (int i = 0; i < this.mRowViewArray.size(); i++) {
				rowView = this.mRowViewArray.valueAt(i);
				addView(rowView);
				rowView.notifyDataChanged();
				while (rowView.hasNext()) {
					rowView = rowView.getNext();
					addView(rowView);
					rowView.notifyDataChanged();
				}
			}
			afreshRowViewSelector();
		} else {
			setVisibility(View.GONE);
		}
	}

	/**
	 * 重新刷新选择器
	 */
	private void afreshRowViewSelector() {
		int count = getChildCount();
		if (count <= 1) {
			try {
				View view = getChildAt(0);
				if (view instanceof RowView) {
					RowView row = (RowView) view;
					row.setRowViewPosition(RowViewPosition.ALL);
					row.notifyDataChanged();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				RowView up = (RowView) getChildAt(0);
				RowView dowm = (RowView) getChildAt(count - 1);
				up.setRowViewPosition(RowViewPosition.UP);
				up.notifyDataChanged();
				dowm.setRowViewPosition(RowViewPosition.DOWM);
				dowm.notifyDataChanged();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void initGroupViewData(Builder builder) {
		mGorupViewTitle = builder.gorupViewTitle;
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
