package com.kubeiwu.commontool.view.util;

import android.widget.LinearLayout;

import com.kubeiwu.commontool.view.core.IRowStyle;
import com.kubeiwu.commontool.view.util.ItemBgSelectorUtil.RowStyle;

/**
 * 选择器的参数
 * 
 * @author Administrator
 */
public class DisplayOptions {
	private DisplayOptions() {
		// 私有构造
	}

	private int normalLineColorId;
	private int normalBackgroundColorId;
	private int pressedLineColorId;
	private int pressedBackgroundColorId;
//	private int circleSize;
	private int linewidth;

	private int rowTitleColorId;
	private int rowTitleSizePx;

	private int groupTitleColorId;
	private int groupTitleSizePx;

	private int rowPaddingStart;

//	private int middleLinePaddingLeft;
	private IRowStyle iRowStyle ;

	public IRowStyle getiRowStyle() {
		return iRowStyle;
	}

//	public int getMiddleLinePaddingLeft() {
//		return middleLinePaddingLeft;
//	}

	public int getRowTitleColorId() {
		return rowTitleColorId;
	}

	public int getRowTitleSizePx() {
		return rowTitleSizePx;
	}

	private int rowPaddingEnd;

	public int getRowPaddingStart() {
		return rowPaddingStart;
	}

	public int getRowPaddingEnd() {
		return rowPaddingEnd;
	}

//	private int rowStyle;
//	private int showDividers;
	private int dividerPadding;

	public int getDividerPadding() {
		return dividerPadding;
	}

//	public int getShowDividers1() {
//		return showDividers;
//	}

//	public int getRowStyle() {
//		return rowStyle;
//	}

	public int getGroupTitleColorId() {
		return groupTitleColorId;
	}

	public int getGroupTitleSizePx() {
		return groupTitleSizePx;
	}

	public int getPressedLineColorId() {
		return pressedLineColorId;
	}

	public int getNormalLineColorId() {
		return normalLineColorId;
	}

	public int getNormalBackgroundColorId() {
		return normalBackgroundColorId;
	}

	public int getPressedBackgroundColorId() {
		return pressedBackgroundColorId;
	}

//	public int getCircleSize() {
//		return circleSize;
//	}

	public int getLinewidth() {
		return linewidth;
	}

	private DisplayOptions(Builder builder) {
		this.normalLineColorId = builder.normalLineColorId;
		this.normalBackgroundColorId = builder.normalBackgroundColorId;
		this.pressedLineColorId = builder.pressedLineColorId;
		this.pressedBackgroundColorId = builder.pressedBackgroundColorId;
//		this.circleSize = builder.circleSize;
		this.linewidth = builder.linewidth;

		this.rowTitleColorId = builder.rowTitleColorId;
		this.rowTitleSizePx = builder.rowTitleSizePx;

		this.groupTitleColorId = builder.groupTitleColorId;
		this.groupTitleSizePx = builder.groupTitleSizePx;
//		this.rowStyle = builder.rowStyle;
		this.rowPaddingStart = builder.rowleftpadding;
		this.rowPaddingEnd = builder.rowPaddingEnd;
//		this.showDividers = builder.showDividers;
		this.dividerPadding = builder.dividerPadding;

//		this.middleLinePaddingLeft = builder.middleLinePaddingLeft;
		this.iRowStyle = builder.iRowStyle;
	}

	public static DisplayOptions createsimpleDisplayOptions() {
		return new Builder().build();
	}

	public static class Builder {

		private int rowPaddingEnd;
		private int normalLineColorId = android.R.color.darker_gray;// 线条颜色资源id FFD6D6D6
		private int normalBackgroundColorId = android.R.color.white;// 默认时候的背景
		private int pressedLineColorId = android.R.color.darker_gray;// 按下时候的线条颜色id
		private int pressedBackgroundColorId = android.R.color.holo_blue_light;// 按下时候的背景颜色资源id
//		private int circleSize = 0; // 圆角大小
		private int linewidth = 1;// 线宽

		private int rowTitleColorId = android.R.color.black; // 行的title字体颜色
		private int rowTitleSizePx = 16; // title 字体大小 px

		private int groupTitleColorId = android.R.color.black; // 组的title字体颜色
		private int groupTitleSizePx = 16; // title 字体大小 px

//		private int rowStyle = RowStyle.ALL_AROUND;

		private int dividerPadding = 0;
//		private int middleLinePaddingLeft = 0;
		private IRowStyle iRowStyle ;

		public Builder setRowStyle(IRowStyle iRowStyle) {
			this.iRowStyle = iRowStyle;
			return this;
		}
//		public Builder setMiddleLinePaddingLeft(int middleLinePaddingLeft) {
//			this.middleLinePaddingLeft = middleLinePaddingLeft;
//			return this;
//		}

		public Builder setDividerPadding(int padding) {
			this.dividerPadding = padding;
			return this;
		}

		public Builder setRowPaddingEnd(int rowPaddingEnd) {
			this.rowPaddingEnd = rowPaddingEnd;
			return this;
		}

		private int rowleftpadding;
//		private int showDividers;

		public Builder setRowleftpadding(int rowleftpadding) {
			this.rowleftpadding = rowleftpadding;
			return this;
		}

		public Builder setNormalLineColorId(int normalLineColorId) {
			this.normalLineColorId = normalLineColorId;
			return this;
		}

		/**
		 * 使用 setDividerResId(int dividerResId)替代
		 * 
		 * @param rowStyle
		 * @return
		 */
//		public Builder setRowStyle(int rowStyle) {
//			this.rowStyle = rowStyle;
//			if (rowStyle == RowStyle.UP_DOWN_AROUND) {// 只设置中间的线条
//				showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE;
//			}
//			return this;
//		}

		public Builder setRowTitleColorId(int rowTitleColorId) {
			this.rowTitleColorId = rowTitleColorId;
			return this;
		}

		public Builder setRowTitleSizePx(int rowTitleSizePx) {
			this.rowTitleSizePx = rowTitleSizePx;
			return this;
		}

		public Builder setGroupTitleColorId(int groupTitleColorId) {
			this.groupTitleColorId = groupTitleColorId;
			return this;
		}

		public Builder setGroupTitleSizePx(int groupTitleSizePx) {
			this.groupTitleSizePx = groupTitleSizePx;
			return this;
		}

		public Builder setNormalBackgroundColorId(int normalBackgroundColorId) {
			this.normalBackgroundColorId = normalBackgroundColorId;
			return this;
		}

		public Builder setPressedLineColorId(int pressedLineColorId) {
			this.pressedLineColorId = pressedLineColorId;
			return this;
		}

		public Builder setPressedBackgroundColorId(int pressedBackgroundColorId) {
			this.pressedBackgroundColorId = pressedBackgroundColorId;
			return this;
		}

//		public Builder setCircleSize(int circleSize) {
//			this.circleSize = circleSize;
//			return this;
//		}

		public Builder setLinewidth(int linewidth) {
			this.linewidth = linewidth;
			return this;
		}

		public DisplayOptions build() {
			return new DisplayOptions(this);
		}
	}
}
