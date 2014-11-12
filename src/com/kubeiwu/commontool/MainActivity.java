package com.kubeiwu.commontool;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kubeiwu.commontool.view.setting.CheckBoxRowView;
import com.kubeiwu.commontool.view.setting.DefaultRowView;
import com.kubeiwu.commontool.view.setting.GroupView;
import com.kubeiwu.commontool.view.setting.KSettingView;
import com.kubeiwu.commontool.view.setting.ListRowView;
import com.kubeiwu.commontool.view.setting.RowView;
import com.kubeiwu.commontool.view.util.DisplayOptions;
import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.ParaSetting;
import com.kubeiwu.commontool.view.util.ParaSetting.ParaUtil;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ParaUtil.initPrar(getApplicationContext());
		setContentView(init4());
	}

	View init2() {
		KSettingView containerView = new KSettingView(getApplicationContext());
		// SetViewUtil setViewUtil=new SetViewUtil(containerView);
		// initSelector(getContext(), R.color.setting_view_item_bg_pressed,
		// android.R.color.white,//
		// android.R.color.holo_blue_light, android.R.color.holo_blue_light);
//		@SuppressWarnings("unused")
//		DisplayOptions selectorPara = new DisplayOptions(android.R.color.darker_gray, android.R.color.white,//
//				android.R.color.holo_blue_light, android.R.color.holo_blue_light, 0, 1);
		// containerView.addItem(1, 10, 1, "缓存", R.drawable.ic_launcher,
		// selectorPara, this);
		// containerView.addItem(1, 20, 2, "收藏", R.drawable.ic_launcher,
		// selectorPara, this);
		// containerView.addItem(1, 30, 2, "历史", R.drawable.ic_launcher,
		// selectorPara, this);
		// containerView.addItem(1, 40, 2, "记录", R.drawable.ic_launcher,
		// selectorPara, this);
		//
		// containerView.addItem(2, 1, 1, "我要爆料", R.drawable.ic_launcher,
		// selectorPara, this);
		// containerView.addItem(2, 2, 2, "爆料记录", R.drawable.ic_launcher,
		// selectorPara, this);
		//
		// containerView.addItem(3, 1, 3, "开通会员", R.drawable.ic_launcher,
		// selectorPara, this);
		// containerView.addItem(3, 2, 4, "为我评分", R.drawable.ic_launcher,
		// selectorPara, this);
		// ListView d；
		// d.setOnItemClickListener(listener);
//		containerView.commit();
		return containerView;
	}

	View init3() {
		KSettingView containerView = new KSettingView(MainActivity.this);
//		DisplayOptions selectorPara = new DisplayOptions(android.R.color.darker_gray, android.R.color.white,//
//				android.R.color.holo_blue_light, android.R.color.holo_blue_light, 0, 1);

		GroupView groupView1 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他1").create();
		GroupView groupView2 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他2").create();
		GroupView groupView3 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他3").create();
		// groupView1.addRowViewItem(1, 1, "缓存", R.drawable.ic_launcher,
		// selectorPara, this);
		// groupView1.addRowViewItem(2, 2, "收藏", R.drawable.ic_launcher,
		// selectorPara, this);
		// groupView1.addRowViewItem(3, 3, "历史", R.drawable.ic_launcher,
		// selectorPara, this);
		//
		// groupView2.addRowViewItem(1, 1, "缓存11", R.drawable.ic_launcher,
		// selectorPara, this);
		// groupView2.addRowViewItem(2, 2, "收藏11", R.drawable.ic_launcher,
		// selectorPara, this);
		// groupView2.addRowViewItem(3, 3, "历史11", R.drawable.ic_launcher,
		// selectorPara, this);
		//
		// groupView3.addRowViewItem(1, 1, "缓存22", R.drawable.ic_launcher,
		// selectorPara, this);
		// groupView3.addRowViewItem(2, 2, "收藏222", R.drawable.ic_launcher,
		// selectorPara, this);
		// groupView3.addRowViewItem(3, 3, "历史222", R.drawable.ic_launcher,
		// selectorPara, this);

		containerView.addGroupView(1, groupView1);
		containerView.addGroupView(1, groupView2);
		containerView.addGroupView(3, groupView3);

		containerView.commit();
		return containerView;
	}

	View init4() {
		KSettingView containerView = new KSettingView(MainActivity.this);
//		DisplayOptions selectorPara = new DisplayOptions(android.R.color.darker_gray, android.R.color.white,//
//				android.R.color.holo_blue_light, android.R.color.holo_blue_light, 0, 1);
		DisplayOptions selectorPara=new DisplayOptions.Builder()//
		.setGroupTitleSizePx(20)
		.build();
		
		containerView.setDisplayOptions(selectorPara);

		GroupView groupView1 = containerView.addGroupViewItem(-1);
		GroupView groupView2 = containerView.addGroupViewItem(2, "其他1");
		GroupView groupView3 = containerView.addGroupViewItem(3, "其他1");

		groupView1.addRowViewItem(CheckBoxRowView.class, 1, "缓存", 0, ParaSetting.xiazshud1);
		groupView1.addRowViewItem(CheckBoxRowView.class, 2, "收藏", R.drawable.ic_launcher, ParaSetting.xiazshud2.key, R.drawable.setting_view_item_selector, ParaSetting.xiazshud2.value);
		// ;
		groupView1.addRowViewItem(CheckBoxRowView.class, 3, "历史", R.drawable.ic_launcher, ParaSetting.xiazshud3.key, R.drawable.setting_view_item_selector, ParaSetting.xiazshud3.value);
		//
		groupView2.addRowViewItem(CheckBoxRowView.class, 1, "缓存11", R.drawable.ic_launcher, ParaSetting.xiazshud4.key, R.drawable.setting_view_item_selector, ParaSetting.xiazshud4.value);
		groupView2.addRowViewItem(CheckBoxRowView.class, 2, "收藏11", R.drawable.ic_launcher, ParaSetting.xiazshud5.key, R.drawable.setting_view_item_selector);
		groupView2.addRowViewItem(CheckBoxRowView.class, 2, "收藏11", R.drawable.ic_launcher, ParaSetting.xiazshud6.key, R.drawable.setting_view_item_selector);
		// groupView2.addRowViewItem(CheckBoxRowView.class, 3, "历史11",
		// R.drawable.ic_launcher,Paraset.xiazshud6.key,
		// R.drawable.setting_view_item_selector);
		//
		groupView3.addRowViewItem(ListRowView.class, 1, "缓存11", R.drawable.ic_launcher, ParaSetting.xiazshud7.key, R.drawable.arrow_to_right, 1)//
				.setEntries("aaa", "bbb", "ccc", "ddd", "eee")//
				.setEntryValues(1, 2, 3, 4, 5)//
				.setOnRowClickListener(new OnRowClickListener<ListRowView>() {

					@Override
					public void onRowClick(ListRowView t, RowViewActionEnum action) {
						// t.showDialog(MainActivity.this);
						// t.getKey();
						// t.getEntry();
						System.out.println("t.getEntry();" + t.getEntry());
						System.out.println(ParaSetting.xiazshud7.value);

					}
				}).setPara(ParaSetting.xiazshud7);
		groupView3.addRowViewItem(DefaultRowView.class, 2, "收藏11", R.drawable.ic_launcher, "key8", R.drawable.setting_view_item_selector, "得的");
		groupView3.addRowViewItem(DefaultRowView.class, 3, "历史11", R.drawable.ic_launcher, "key9", R.drawable.arrow_to_right, "哈哈");

		containerView.commit();
		return containerView;
	}

	AlertDialog alertDialog;

}
