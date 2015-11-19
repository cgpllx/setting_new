package com.kubeiwu.commontool.view.util;

import com.kubeiwu.commontool.view.setting.GroupView.RowViewPosition;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;

public class ItemBgSelectorUtil {
	/**
	 * 设置Selector
	 */
	@SuppressWarnings("deprecation")
	public static StateListDrawable newSelector(Context context, int idNormal, int idPressed, int idFocused, int idUnable) {
		StateListDrawable bg = new StateListDrawable();
		Drawable normal = idNormal == -1 ? null : context.getResources().getDrawable(idNormal);
		Drawable pressed = idPressed == -1 ? null : context.getResources().getDrawable(idPressed);
		Drawable focused = idFocused == -1 ? null : context.getResources().getDrawable(idFocused);
		Drawable unable = idUnable == -1 ? null : context.getResources().getDrawable(idUnable);
		// View.PRESSED_ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);
		// View.ENABLED_FOCUSED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focused);
		// View.ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_enabled }, normal);
		// View.FOCUSED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_focused }, focused);
		// View.WINDOW_FOCUSED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_window_focused }, unable);
		// View.EMPTY_STATE_SET
		bg.addState(new int[] {}, normal);
		return bg;
	}

	/** 设置Selector。 */
	public static StateListDrawable newSelector(Drawable normal, Drawable pressed, Drawable focused, Drawable unable) {
		StateListDrawable bg = new StateListDrawable();
		// View.PRESSED_ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled }, pressed);
		// View.ENABLED_FOCUSED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_enabled, android.R.attr.state_focused }, focused);
		// View.ENABLED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_enabled }, normal);
		// View.FOCUSED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_focused }, focused);
		// View.WINDOW_FOCUSED_STATE_SET
		bg.addState(new int[] { android.R.attr.state_window_focused }, unable);
		// View.EMPTY_STATE_SET
		bg.addState(new int[] {}, normal);
		return bg;
	}

	/** 对TextView设置不同状态时其文字颜色。 */
	public ColorStateList createColorStateList(int normal, int pressed, int focused, int unable) {
		int[] colors = new int[] { pressed, focused, normal, focused, unable, normal };
		int[][] states = new int[6][];
		states[0] = new int[] { android.R.attr.state_pressed, android.R.attr.state_enabled };
		states[1] = new int[] { android.R.attr.state_enabled, android.R.attr.state_focused };
		states[2] = new int[] { android.R.attr.state_enabled };
		states[3] = new int[] { android.R.attr.state_focused };
		states[4] = new int[] { android.R.attr.state_window_focused };
		states[5] = new int[] {};
		ColorStateList colorList = new ColorStateList(states, colors);
		return colorList;
	}

	/**
	 * 根据颜色资源创建带圆角的资源Drawable
	 * 
	 * @param context
	 * @param idRes
	 *            color资源
	 * @param rowViewPosition
	 *            RowViewPosition.UP MIDDLE DOWM ALL
	 * @return
	 */
	public static Drawable getDrawableFromResId(Context context, int idRes, int rowViewPosition, boolean bg, int out_circle_Size, int linewidth) {
		GradientDrawable gd = new GradientDrawable();// 创建drawable
		int inner_circle_size = out_circle_Size - linewidth < 0 ? 0 : out_circle_Size - linewidth;
		int circle_size = bg ? inner_circle_size : out_circle_Size;// 内圆半径为外圆减线宽
		switch (rowViewPosition) {
			case RowViewPosition.UP:
				gd.setCornerRadii(new float[] { circle_size, circle_size, circle_size, circle_size, //
						0, 0, 0, 0 });
				break;
			case RowViewPosition.MIDDLE:
				gd.setCornerRadii(new float[] { 0, 0, 0, 0, 0, 0, 0, 0 });
				break;
			case RowViewPosition.DOWM:
				gd.setCornerRadii(new float[] { 0, 0, 0, 0, circle_size, circle_size, circle_size, circle_size });// 长度是8
				break;
			case RowViewPosition.ALL:
				gd.setCornerRadii(new float[] { circle_size, circle_size, circle_size, circle_size, circle_size, //
						circle_size, circle_size, circle_size });//
				break;
		}
		// gd.setCornerRadii(radii);// 长度是8
		// float roundRadius = 25;
		// gd.setCornerRadius(roundRadius);// 设置圆角
		gd.setShape(GradientDrawable.RECTANGLE);// 设置形状为矩形
		gd.setUseLevel(true);
		// gd.setSize(width, height);
		// gd.setGradientRadius(25);
		int color = context.getResources().getColor(idRes);
		gd.setColor(color);
		// gd.setAlpha(1);
		return gd;
	}
	public static Drawable getLineDrawable(Drawable lineColor, Drawable backgroundColor,int middleLinePaddingLeft) {
		Drawable[] layers = new Drawable[] { backgroundColor, lineColor };
		LayerDrawable layerDrawable = new LayerDrawable(layers);
		layerDrawable.setLayerInset(0, 0, 0, 0, 0);
		layerDrawable.setLayerInset(1, middleLinePaddingLeft, 0, 0, 0);
		return layerDrawable;
	}
	/**
	 * color转 drawable，主要处理线条用
	 * 
	 * @param context
	 * @param idRes
	 * @param rowViewPosition
	 * @param bg
	 * @return
	 */
	public static Drawable getLineDrawableFromResId(Context context, int idRes,int linewidth) {
		GradientDrawable gd = new GradientDrawable();// 创建drawable
		gd.setShape(GradientDrawable.RECTANGLE);// 设置形状为矩形
		gd.setUseLevel(true);
		gd.setSize(0, linewidth);
		int color = context.getResources().getColor(idRes);
		gd.setColor(color);
		// gd.setCornerRadii(new float[] { 0, 0, 0, 0, 0, 0, 0, 0 });
		// gd.setAlpha(1);
		return gd;
	}
}
