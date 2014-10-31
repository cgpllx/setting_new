package com.kubeiwu.commontool.view.setting;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;

import com.kubeiwu.commontool.view.util.DisplayRowViewOptions;
import com.kubeiwu.commontool.view.util.ItemBgSelectorUtil;

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

	// mWidgetRow_Label.setTextColor(getResources().getColor(selectorPara.getTitleColorId()));
	// mWidgetRow_Label.setTextSize(selectorPara.getTitleSizePx());
	/**
	 * 增加单个RowView
	 * 
	 * @param order
	 * @param rowView
	 */
	@SuppressLint("NewApi")
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int order, int itemId, String rowTitle, int iconResourceId, String key, int resId, DisplayRowViewOptions selectorPara) {
		RowView entry = this.mRowViewArray.get(order);
		T rowView = new RowView.Builder<T>(getContext()).setItemId(itemId).setIconResourceId(iconResourceId)//
				.setLable(rowTitle)//
				.setKey(key)//
				.setResId(resId)//
				.create(clazz);
		// rowView.setBackground(middleSelector);
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
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int iconResourceId, String key, int resId, DisplayRowViewOptions selectorPara) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, iconResourceId, key, resId, selectorPara);
	}

	/**
	 * 增加单个RowView 在0的位置
	 * 
	 * @param rowView
	 */
	public <T extends RowView> T addRowViewItem(Class<T> clazz, int itemId, String rowTitle, int iconResourceId, int resId, DisplayRowViewOptions selectorPara) {
		return addRowViewItem(clazz, 0, itemId, rowTitle, iconResourceId, null, resId, selectorPara);
	}

	private void addView(RowView rowView) {
		super.addView(rowView);
		rowView.getRowViewTitle().setTextColor(getResources().getColor(selectorPara.getTitleColorId()));
		rowView.getRowViewTitle().setTextSize(selectorPara.getTitleSizePx());
		rowView.notifyDataChanged();
	}

	public void notifyDataChanged() {
		if (this.mRowViewArray != null && this.mRowViewArray.size() > 0) {
			RowView rowView = null;
			for (int i = 0; i < this.mRowViewArray.size(); i++) {
				rowView = this.mRowViewArray.valueAt(i);
				addView(rowView);
				while (rowView.hasNext()) {
					rowView = rowView.getNext();
					addView(rowView);
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
	@SuppressLint("NewApi")
	private void afreshRowViewSelector() {
		int count = getChildCount();
		if (count <= 1) {
			getChildAt(0).setBackground(creatDrawable(RowViewPosition.ALL));
		} else {
			getChildAt(0).setBackground(creatDrawable(RowViewPosition.UP));
			for (int i = 1; i < count - 1; i++) {
				getChildAt(i).setBackground(creatDrawable(RowViewPosition.MIDDLE));
			}
			getChildAt(count - 1).setBackground(creatDrawable(RowViewPosition.DOWM));
		}
	}

	private DisplayRowViewOptions selectorPara;

	private void initGroupViewData(Builder builder) {
		mGorupViewTitle = builder.gorupViewTitle;
		selectorPara = builder.selectorPara;
		if (selectorPara == null) {
			selectorPara = new DisplayRowViewOptions();
		}
	}

	private Drawable creatDrawable(int rowViewPosition) {
		ItemBgSelectorUtil itemBgSelectorUtil = new ItemBgSelectorUtil(selectorPara.getOut_circle_Size(), selectorPara.getLinewidth());
		return itemBgSelectorUtil.createSelector(getContext(), //
				selectorPara.getNormalLineColorId(),//
				selectorPara.getNormalBackgroundColorId(),//
				selectorPara.getPressedLineColorId(), //
				selectorPara.getPressedBackgroundColorId(),//
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
		private DisplayRowViewOptions selectorPara;// 封装RowView相同的参数

		public Builder(Context context) {
			this.context = context;
		}

		public Builder setGorupViewTitle(String gorupViewTitle) {
			this.gorupViewTitle = gorupViewTitle;
			return this;
		}

		public Builder setSelectorPara(DisplayRowViewOptions selectorPara) {
			this.selectorPara = selectorPara;
			return this;
		}

		public GroupView create() {
			final GroupView groupView = new GroupView(context);
			groupView.initGroupViewData(this);
			return groupView;
		}
	}

}
