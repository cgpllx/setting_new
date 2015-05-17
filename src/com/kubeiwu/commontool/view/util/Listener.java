package com.kubeiwu.commontool.view.util;

import com.kubeiwu.commontool.view.setting.GroupView;
import com.kubeiwu.commontool.view.setting.RowView;

public class Listener {
	public interface OnGroupViewItemClickListener {

		void onItemClick(GroupView groupView, RowView rowView);
	}

	public interface OnRowViewClickListener {

		void onRowViewClick(RowView rowView);
	}
}
