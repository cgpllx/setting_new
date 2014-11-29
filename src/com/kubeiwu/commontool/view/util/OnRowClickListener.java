package com.kubeiwu.commontool.view.util;

import com.kubeiwu.commontool.view.setting.RowView;

public interface OnRowClickListener<T extends RowView> {
	void onRowClick(T rowView,RowViewActionEnum action);
}
