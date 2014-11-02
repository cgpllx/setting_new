package com.kubeiwu.commontool.view.setting;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kubeiwu.commontool.view.util.DisplayOptions;

public class KSettingView extends LinearLayout {

	public KSettingView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView();
	}

	private void initView() {
		setOrientation(VERTICAL);
		setPadding(15, 10, 15, 10);
		LayoutParams l = new LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
		// l.setMargins(20, 10, 20, 10);
		setLayoutParams(l);

	}

	public KSettingView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public KSettingView(Context context) {
		super(context);
		initView();
	}
	private DisplayOptions displayOptions;
	public void setDisplayOptions(DisplayOptions displayOptions) {
		this.displayOptions=displayOptions;
	}

	private SparseArray<GroupView> mGroupViewArray = new SparseArray<GroupView>();

	/**
	 * 增加一个GroupView到容器中
	 * 
	 * @param groupOrder
	 *            排序id
	 * @param gorupViewTitle
	 *            title
	 * @return 返回被增加的GroupView
	 */
	public GroupView addGroupViewItem(int groupOrder, String gorupViewTitle) {
		GroupView entry = mGroupViewArray.get(groupOrder);
		GroupView groupView = new GroupView.Builder(getContext()).setGorupViewTitle(gorupViewTitle).create();
		if (entry == null) {
			mGroupViewArray.put(groupOrder, groupView);
		} else {
			entry.addGroupViewLastNode(groupView);
		}
		return groupView;
	}

	/**
	 * 增加一个不需要title的GroupView到容器中
	 * 
	 * @param groupOrder
	 * @return 返回被增加的GroupView
	 */
	public GroupView addGroupViewItem(int groupOrder) {
		return addGroupViewItem(groupOrder, null);
	}

	/**
	 * 在设置view中增加多组GroupView
	 * 
	 * @param groupViewArray
	 * @deprecated
	 */
	public void addGroupViewArray(SparseArray<GroupView> groupViewArray) {
		if (groupViewArray == null || groupViewArray.size() == 0) {
			this.mGroupViewArray = groupViewArray;
		} else {
			for (int i = 0; i < groupViewArray.size(); i++) {
				// 传进来的key 和value
				int key = groupViewArray.keyAt(i);
				GroupView entry = groupViewArray.valueAt(i);
				// 当前容器中的value
				GroupView groupView = this.mGroupViewArray.get(key);
				if (groupView == null) {// 当前容器中不存在就直接加入
					this.mGroupViewArray.put(key, entry);
				} else {
					groupView.addGroupView(entry);
				}
			}
		}
	}

	/**
	 * 向容器中增加一个GroupView，如果groupOrder位置 在容器中已经存在，则会在下面增加
	 * 
	 * @param groupViewArray
	 */
	public void addGroupView(int groupOrder, GroupView groupView) {
		GroupView delivery = this.mGroupViewArray.get(groupOrder);
		if (delivery == null) {
			this.mGroupViewArray.put(groupOrder, groupView);
		} else {
			delivery.addGroupViewLastNode(groupView);
		}

	}

	public void commit() {
		notifyDataChanged();
	}

	// 重载addview
	public void addView(GroupView child) {
		String title = child.getTitle();
		if (!TextUtils.isEmpty(title)) {
			TextView textview = new TextView(getContext());
			textview.setText(title);
			addView(textview);
		}
		child.setDisplayOptions(displayOptions);
		super.addView(child);
		child.notifyDataChanged();
	}

	public void notifyDataChanged() {
		if (mGroupViewArray != null && mGroupViewArray.size() > 0) {
			GroupView groupView = null;
			for (int i = 0; i < mGroupViewArray.size(); i++) {
				groupView = mGroupViewArray.valueAt(i);
				addView(groupView);
				while (groupView.hasNext()) {
					groupView = groupView.getNext();
					addView(groupView);
				}
			}
		} else {
			setVisibility(View.GONE);
		}
	}
}
