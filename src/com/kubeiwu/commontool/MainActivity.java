package com.kubeiwu.commontool;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.kubeiwu.commontool.view.setting.CheckBoxRowView;
import com.kubeiwu.commontool.view.setting.DefaultRowView;
import com.kubeiwu.commontool.view.setting.GroupView;
import com.kubeiwu.commontool.view.setting.KSettingView;
import com.kubeiwu.commontool.view.setting.RowView;
import com.kubeiwu.commontool.view.util.DisplayRowViewOptions;
import com.kubeiwu.commontool.view.util.OnRowClickListener;
import com.kubeiwu.commontool.view.util.RowViewActionEnum;

public class MainActivity extends Activity implements OnRowClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(init4());
	}

	View init2() {
		KSettingView containerView = new KSettingView(getApplicationContext());
		// SetViewUtil setViewUtil=new SetViewUtil(containerView);
		// initSelector(getContext(), R.color.setting_view_item_bg_pressed, android.R.color.white,//
		// android.R.color.holo_blue_light, android.R.color.holo_blue_light);
		DisplayRowViewOptions selectorPara = new DisplayRowViewOptions(android.R.color.darker_gray, android.R.color.white,//
				android.R.color.holo_blue_light, android.R.color.holo_blue_light, 0, 1);
		// containerView.addItem(1, 10, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItem(1, 20, 2, "收藏", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItem(1, 30, 2, "历史", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItem(1, 40, 2, "记录", R.drawable.ic_launcher, selectorPara, this);
		//
		// containerView.addItem(2, 1, 1, "我要爆料", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItem(2, 2, 2, "爆料记录", R.drawable.ic_launcher, selectorPara, this);
		//
		// containerView.addItem(3, 1, 3, "开通会员", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItem(3, 2, 4, "为我评分", R.drawable.ic_launcher, selectorPara, this);
		// ListView d；
		// d.setOnItemClickListener(listener);
		containerView.commit();
		return containerView;
	}

	View init3() {
		KSettingView containerView = new KSettingView(getApplicationContext());
		DisplayRowViewOptions selectorPara = new DisplayRowViewOptions(android.R.color.darker_gray, android.R.color.white,//
				android.R.color.holo_blue_light, android.R.color.holo_blue_light, 0, 1);

		GroupView groupView1 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他1").create();
		GroupView groupView2 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他2").create();
		GroupView groupView3 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他3").create();
		// groupView1.addRowViewItem(1, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// groupView1.addRowViewItem(2, 2, "收藏", R.drawable.ic_launcher, selectorPara, this);
		// groupView1.addRowViewItem(3, 3, "历史", R.drawable.ic_launcher, selectorPara, this);
		//
		// groupView2.addRowViewItem(1, 1, "缓存11", R.drawable.ic_launcher, selectorPara, this);
		// groupView2.addRowViewItem(2, 2, "收藏11", R.drawable.ic_launcher, selectorPara, this);
		// groupView2.addRowViewItem(3, 3, "历史11", R.drawable.ic_launcher, selectorPara, this);
		//
		// groupView3.addRowViewItem(1, 1, "缓存22", R.drawable.ic_launcher, selectorPara, this);
		// groupView3.addRowViewItem(2, 2, "收藏222", R.drawable.ic_launcher, selectorPara, this);
		// groupView3.addRowViewItem(3, 3, "历史222", R.drawable.ic_launcher, selectorPara, this);

		containerView.addGroupView(1, groupView1);
		containerView.addGroupView(1, groupView2);
		containerView.addGroupView(3, groupView3);

		containerView.commit();
		return containerView;
	}

	View init4() {
		KSettingView containerView = new KSettingView(getApplicationContext());
		DisplayRowViewOptions selectorPara = new DisplayRowViewOptions(android.R.color.darker_gray, android.R.color.white,//
				android.R.color.holo_blue_light, android.R.color.holo_blue_light, 0, 1);

		// GroupView groupView1 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他1").create();
		// GroupView groupView2 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他2").create();
		// GroupView groupView3 = new GroupView.Builder(getApplicationContext()).setGorupViewTitle("其他3").create();
		// // groupView1.addRowViewItem(1, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// groupView1.addRowViewItem(2, 2, "收藏", R.drawable.ic_launcher, selectorPara, this);
		// groupView1.addRowViewItem(3, 3, "历史", R.drawable.ic_launcher, selectorPara, this);
		//
		// groupView2.addRowViewItem(1, 1, "缓存11", R.drawable.ic_launcher, selectorPara, this);
		// groupView2.addRowViewItem(2, 2, "收藏11", R.drawable.ic_launcher, selectorPara, this);
		// groupView2.addRowViewItem(3, 3, "历史11", R.drawable.ic_launcher, selectorPara, this);
		//
		// groupView3.addRowViewItem(1, 1, "缓存22", R.drawable.ic_launcher, selectorPara, this);
		// groupView3.addRowViewItem(2, 2, "收藏222", R.drawable.ic_launcher, selectorPara, this);
		// groupView3.addRowViewItem(3, 3, "历史222", R.drawable.ic_launcher, selectorPara, this);
		//
		GroupView groupView1 = containerView.addGroupViewItem(-1);
		GroupView groupView2 = containerView.addGroupViewItem(2, "其他1");
		GroupView groupView3 = containerView.addGroupViewItem(3, "其他1");

		groupView1.addRowViewItem(CheckBoxRowView.class, 1, "缓存", R.drawable.ic_launcher, "key1", 0, selectorPara).setValue(false);
		groupView1.addRowViewItem(CheckBoxRowView.class, 2, "收藏", R.drawable.ic_launcher, "key2", R.drawable.setting_view_item_selector, selectorPara).setValue(true);;
		groupView1.addRowViewItem(CheckBoxRowView.class, 3, "历史", R.drawable.ic_launcher, "key3", R.drawable.setting_view_item_selector, selectorPara);

		groupView2.addRowViewItem(CheckBoxRowView.class, 1, "缓存11", R.drawable.ic_launcher, "key41", R.drawable.setting_view_item_selector, selectorPara);
		groupView2.addRowViewItem(CheckBoxRowView.class, 2, "收藏11", R.drawable.ic_launcher, "key5", R.drawable.setting_view_item_selector, selectorPara);
		groupView2.addRowViewItem(CheckBoxRowView.class, 3, "历史11", R.drawable.ic_launcher, "key6", R.drawable.setting_view_item_selector, selectorPara);

		groupView3.addRowViewItem(DefaultRowView.class, 1, "缓存11", R.drawable.ic_launcher, "key7", R.drawable.arrow_to_right, selectorPara);
		groupView3.addRowViewItem(DefaultRowView.class, 2, "收藏11", R.drawable.ic_launcher, "key8", R.drawable.setting_view_item_selector, selectorPara);
		groupView3.addRowViewItem(DefaultRowView.class, 3, "历史11", R.drawable.ic_launcher, "key9", R.drawable.arrow_to_right, selectorPara).setOnRowClickListener(new OnRowClickListener<DefaultRowView>() {

			@Override
			public void onRowClick(DefaultRowView t, RowViewActionEnum action) {
				// TODO Auto-generated method stub
				
			}
		});

		// groupView3.addRowViewItem(1, 1, "缓存22", R.drawable.ic_launcher, "key",selectorPara, this);
		// groupView3.addRowViewItem(2, 2, "收藏222", R.drawable.ic_launcher,"key", selectorPara, this);
		// groupView3.addRowViewItem(3, 3, "历史222", R.drawable.ic_launcher,"key",selectorPara, this);
		// containerView.addItemToExistGroupView(-1, 1, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItemToExistGroupView(-1, 2, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItemToExistGroupView(-1, 2, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItemToExistGroupView(2, 2, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItemToExistGroupView(2, 2, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItemToExistGroupView(3, 1, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);
		// containerView.addItemToExistGroupView(3, 1, 1, "缓存", R.drawable.ic_launcher, selectorPara, this);

		containerView.commit();
		return containerView;
	}

	@Override
	public void onRowClick(RowView rowView, RowViewActionEnum action) {
		Toast.makeText(getApplicationContext(), rowView.getItemId() + "", 1).show();
	}
}
