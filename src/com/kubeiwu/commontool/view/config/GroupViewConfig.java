package com.kubeiwu.commontool.view.config;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import com.kubeiwu.commontool.view.core.AllAroundStyle;
import com.kubeiwu.commontool.view.core.IRowStyle;
import com.kubeiwu.commontool.view.core.UpDownAroundStyle;
import com.kubeiwu.commontool.view.setting.GroupView.RowViewPosition;
import com.kubeiwu.commontool.view.util.ItemBgSelectorUtil;

/**
 * 选择器的工具类
 * 
 * @author Administrator
 *
 */
public class GroupViewConfig {
	private int outCircleSize = 0;
	private int linewidth = 1;
	private int rowstyle = RowStyle.ALL_AROUND;
	private int middleLinePaddingLeft;

	private int normalLineColorId;
	private int normalBackgroundColorId;
	private int pressedLineColorId;
	private int pressedBackgroundColorId;

	// 外部调用
	private int dividerPadding;
	private int rowPaddingStart;
	private int rowPaddingEnd;
	private int rowTitleColorId;
	private int rowTitleSizePx;
	private int unit;

	public int getUnit() {
		return unit;
	}

	public int getRowTitleColorId() {
		return rowTitleColorId;
	}

	public int getRowTitleSizePx() {
		return rowTitleSizePx;
	}

	public int getNormalLineColorId() {
		return normalLineColorId;
	}

	public int getRowPaddingStart() {
		return rowPaddingStart;
	}

	public int getRowPaddingEnd() {
		return rowPaddingEnd;
	}

	public int getDividerPadding() {
		return dividerPadding;
	}

	public interface RowStyle {
		// UP_DOWN_AROUND used id line divider
		public int ALL_AROUND = 0, UP_DOWN_AROUND = 1;
	}

	public GroupViewConfig(DisplayOptions displayOptions) {
		normalLineColorId = displayOptions.getNormalLineColorId();
		normalBackgroundColorId = displayOptions.getNormalBackgroundColorId();
		pressedLineColorId = displayOptions.getPressedLineColorId();
		pressedBackgroundColorId = displayOptions.getPressedBackgroundColorId();
		linewidth = displayOptions.getLinewidth();
		dividerPadding = displayOptions.getDividerPadding();
		rowPaddingStart = displayOptions.getRowPaddingStart();
		rowPaddingEnd = displayOptions.getRowPaddingEnd();
		rowTitleColorId = displayOptions.getRowTitleColorId();
		rowTitleSizePx = displayOptions.getRowTitleSizePx();
		unit = displayOptions.getUnit();

		parseRowStyle(displayOptions.getiRowStyle());
	}

	private void parseRowStyle(IRowStyle iRowStyle) {
		if (iRowStyle instanceof AllAroundStyle) {
			AllAroundStyle allAroundStyle = (AllAroundStyle) iRowStyle;
			outCircleSize = allAroundStyle.circleSize;
			rowstyle = RowStyle.ALL_AROUND;
		} else if (iRowStyle instanceof UpDownAroundStyle) {
			UpDownAroundStyle allAroundStyle = (UpDownAroundStyle) iRowStyle;
			middleLinePaddingLeft = allAroundStyle.middleLinePaddingLeft;
			rowstyle = RowStyle.UP_DOWN_AROUND;
		}

	}

	public Drawable getDrawable(Drawable lineColor, Drawable backgroundColor, int rowViewPosition) {
		Drawable[] layers = new Drawable[] { lineColor, backgroundColor };
		LayerDrawable layerDrawable = new LayerDrawable(layers);
		layerDrawable.setLayerInset(0, 0, 0, 0, 0);
		switch (rowViewPosition) {
			case RowViewPosition.UP:
				if (rowstyle == RowStyle.ALL_AROUND) {
					layerDrawable.setLayerInset(1, linewidth, linewidth, linewidth, linewidth);
				} else if (rowstyle == RowStyle.UP_DOWN_AROUND) {
					layerDrawable.setLayerInset(1, 0, linewidth, 0, 0);// 中间的线条留给divider
				}
				break;
			case RowViewPosition.ALL:
				if (rowstyle == RowStyle.ALL_AROUND) {
					layerDrawable.setLayerInset(1, linewidth, linewidth, linewidth, linewidth);
				} else if (rowstyle == RowStyle.UP_DOWN_AROUND) {
					layerDrawable.setLayerInset(1, 0, 0, 0, 0);// 中间的线条留给divider
				}
				break;
			case RowViewPosition.MIDDLE:
				if (rowstyle == RowStyle.ALL_AROUND) {
					layerDrawable.setLayerInset(1, linewidth, 0, linewidth, linewidth);
				} else if (rowstyle == RowStyle.UP_DOWN_AROUND) {
					layerDrawable.setLayerInset(1, 0, 0, 0, 0);// 中间的线条留给divider
				}
				break;
			case RowViewPosition.DOWM:
				if (rowstyle == RowStyle.ALL_AROUND) {
					layerDrawable.setLayerInset(1, linewidth, 0, linewidth, linewidth);
				} else if (rowstyle == RowStyle.UP_DOWN_AROUND) {
					layerDrawable.setLayerInset(1, 0, 0, 0, linewidth);// 中间的线条留给divider
				}
				break;
		}
		return layerDrawable;
	}

	public Drawable createSelector(Drawable normalLineColor, Drawable normalBackgroundColor, //
			Drawable pressedLineColor, Drawable pressedBackgroundColor, int rowViewPosition) {
		Drawable normal = getDrawable(normalLineColor, normalBackgroundColor, rowViewPosition);// 默认
		Drawable pressed = getDrawable(pressedLineColor, pressedBackgroundColor, rowViewPosition);// 按下
		Drawable focused = getDrawable(pressedLineColor, pressedBackgroundColor, rowViewPosition);// 获取焦点
		Drawable unable = getDrawable(pressedLineColor, pressedBackgroundColor, rowViewPosition);// 不能点击的
		return ItemBgSelectorUtil.newSelector(normal, pressed, focused, unable);// 返回的drawble是选择器的drawble，也就是GradientDrawable
	}

	private Drawable dividerDrawable;

	public Drawable getLineDrawable(Context context) {
		if (dividerDrawable == null) {
			Drawable drawablebg = ItemBgSelectorUtil.getLineDrawableFromResId(context, normalBackgroundColorId, linewidth);
			Drawable drawableline = ItemBgSelectorUtil.getLineDrawableFromResId(context, normalLineColorId, linewidth);
			dividerDrawable = ItemBgSelectorUtil.getLineDrawable(drawableline, drawablebg, middleLinePaddingLeft);
		}
		return dividerDrawable;
	}

	public boolean isShowDivider() {
		return rowstyle == RowStyle.UP_DOWN_AROUND;
	}

	private Drawable createSelector(Context context, int idNormalLineColor, int idNormalBackgroundColor, int idPressedLineColor, int idPressedBackgroundColor, int rowViewPosition) {
		Drawable normalLineColor = ItemBgSelectorUtil.getDrawableFromResId(context, idNormalLineColor, rowViewPosition, false, outCircleSize, linewidth);
		Drawable normalBackgroundColor = ItemBgSelectorUtil.getDrawableFromResId(context, idNormalBackgroundColor, rowViewPosition, true, outCircleSize, linewidth);
		Drawable pressedLineColor = ItemBgSelectorUtil.getDrawableFromResId(context, idPressedLineColor, rowViewPosition, false, outCircleSize, linewidth);
		Drawable pressedBackgroundColor = ItemBgSelectorUtil.getDrawableFromResId(context, idPressedBackgroundColor, rowViewPosition, true, outCircleSize, linewidth);
		return createSelector(normalLineColor, normalBackgroundColor, pressedLineColor, pressedBackgroundColor, rowViewPosition);
	}

	/**
	 * 选择器不能被缓存
	 * 
	 * @param context
	 * @param rowViewPosition
	 * @return
	 */
	public Drawable createSelector(Context context, int rowViewPosition) {
		return createSelector(context, normalLineColorId, normalBackgroundColorId, pressedLineColorId, pressedBackgroundColorId, rowViewPosition);
	}

}
