package com.kubeiwu.commontool.view.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;

import com.kubeiwu.commontool.view.setting.GroupView.RowViewPosition;

/**
 * 选择器的工具类
 * 
 * @author Administrator
 *
 */
public class ItemBgSelectorUtil {
	private int out_circle_Size = 0;
	private int linewidth = 1;
	private int rowstyle = RowStyle.ALL_AROUND;

	public interface RowStyle {
		public int ALL_AROUND = 0, UP_DOWN_AROUND = 1;
	}

	public ItemBgSelectorUtil() {
	}

	public void setRowstyle(int rowstyle) {
		this.rowstyle = rowstyle;
	}

	public void setOut_circle_Size(int out_circle_Size) {
		this.out_circle_Size = out_circle_Size;
	}

	public void setLinewidth(int linewidth) {
		this.linewidth = linewidth;
	}

	public ItemBgSelectorUtil(int out_circle_Size, int linewidth, int rowstyle) {
		super();
		this.out_circle_Size = out_circle_Size;
		this.linewidth = linewidth;
		this.rowstyle = rowstyle;
	}

	public Drawable getDrawable(Drawable lineColor, Drawable backgroundColor, int rowViewPosition) {
		Drawable[] layers = new Drawable[] { lineColor, backgroundColor };
		LayerDrawable layerDrawable = new LayerDrawable(layers);
		switch (rowViewPosition) {
			case RowViewPosition.UP:
			case RowViewPosition.ALL:
				layerDrawable.setLayerInset(0, 0, 0, 0, 0);
				layerDrawable.setLayerInset(1, rowstyle == RowStyle.ALL_AROUND ? linewidth : 0, linewidth, rowstyle == RowStyle.ALL_AROUND ? linewidth : 0, linewidth);
				break;
			case RowViewPosition.MIDDLE:
			case RowViewPosition.DOWM:
				layerDrawable.setLayerInset(0, 0, 0, 0, 0);
				layerDrawable.setLayerInset(1, rowstyle == RowStyle.ALL_AROUND ? linewidth : 0, 0, rowstyle == RowStyle.ALL_AROUND ? linewidth : 0, linewidth);
				break;
		}
		return layerDrawable;
	}

	/**
	 * 设置Selector
	 */
	public StateListDrawable newSelector(Context context, int idNormal, int idPressed, int idFocused, int idUnable) {
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

	public Drawable createSelector(Drawable normalLineColor, Drawable normalBackgroundColor, //
			Drawable pressedLineColor, Drawable pressedBackgroundColor, int rowViewPosition) {
		Drawable normal = getDrawable(normalLineColor, normalBackgroundColor, rowViewPosition);// 默认
		Drawable pressed = getDrawable(pressedLineColor, pressedBackgroundColor, rowViewPosition);// 按下
		Drawable focused = getDrawable(pressedLineColor, pressedBackgroundColor, rowViewPosition);// 获取焦点
		Drawable unable = getDrawable(pressedLineColor, pressedBackgroundColor, rowViewPosition);// 不能点击的
		return newSelector(normal, pressed, focused, unable);// 返回的drawble是选择器的drawble，也就是GradientDrawable
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
	public Drawable getDrawableFromResId(Context context, int idRes, int rowViewPosition, boolean bg) {
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
		// gd.setGradientRadius(25);
		int color = context.getResources().getColor(idRes);
		gd.setColor(color);
		gd.setAlpha(1);
		return gd;
	}

	public Drawable createSelector(Context context, int idNormalLineColor, int idNormalBackgroundColor, int idPressedLineColor, int idPressedBackgroundColor, int rowViewPosition) {
		Drawable normalLineColor = getDrawableFromResId(context, idNormalLineColor, rowViewPosition, false);
		Drawable normalBackgroundColor = getDrawableFromResId(context, idNormalBackgroundColor, rowViewPosition, true);
		Drawable pressedLineColor = getDrawableFromResId(context, idPressedLineColor, rowViewPosition, false);
		Drawable pressedBackgroundColor = getDrawableFromResId(context, idPressedBackgroundColor, rowViewPosition, true);
		return createSelector(normalLineColor, normalBackgroundColor, pressedLineColor, pressedBackgroundColor, rowViewPosition);
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
}
