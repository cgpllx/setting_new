package com.kubeiwu.commontool.view.util;

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
	private int out_circle_Size;
	private int linewidth = 1;

	private int rowTitleColorId;
	private int rowTitleSizePx;

	private int groupTitleColorId;
	private int groupTitleSizePx;

	private int rowPaddingStart;

	// private setDividerDrawable
	private int dividerResId;

	public int getDividerResId() {
		return dividerResId;
	}

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

	private int rowStyle;
	private int showDividers;
	private int dividerPadding;

	public int getDividerPadding() {
		return dividerPadding;
	}

	public int getShowDividers() {
		return showDividers;
	}

	public int getRowStyle() {
		return rowStyle;
	}

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

	public int getOut_circle_Size() {
		return out_circle_Size;
	}

	public int getLinewidth() {
		return linewidth;
	}

	private DisplayOptions(Builder builder) {
		this.normalLineColorId = builder.normalLineColorId;
		this.normalBackgroundColorId = builder.normalBackgroundColorId;
		this.pressedLineColorId = builder.pressedLineColorId;
		this.pressedBackgroundColorId = builder.pressedBackgroundColorId;
		this.out_circle_Size = builder.out_circle_Size;
		this.linewidth = builder.linewidth;

		this.rowTitleColorId = builder.rowTitleColorId;
		this.rowTitleSizePx = builder.rowTitleSizePx;

		this.groupTitleColorId = builder.groupTitleColorId;
		this.groupTitleSizePx = builder.groupTitleSizePx;
		this.rowStyle = builder.rowStyle;
		this.rowPaddingStart = builder.rowleftpadding;
		this.rowPaddingEnd = builder.rowPaddingEnd;
		this.dividerResId = builder.dividerResId;
		this.showDividers = builder.showDividers;
		this.dividerPadding = builder.dividerPadding;
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
		private int out_circle_Size = 0; // 圆角大小
		private int linewidth = 1;// 线宽

		private int rowTitleColorId = android.R.color.black; // 行的title字体颜色
		private int rowTitleSizePx = 16; // title 字体大小 px

		private int groupTitleColorId = android.R.color.black; // 组的title字体颜色
		private int groupTitleSizePx = 16; // title 字体大小 px

		private int rowStyle = RowStyle.ALL_AROUND;

		private int dividerResId = 0;
		private int dividerPadding = 0;

		public Builder setDividerResId(int dividerResId) {
			this.dividerResId = dividerResId;
			return this;
		}

		public Builder setDividerPadding(int padding) {
			this.dividerPadding = padding;
			return this;
		}

		public Builder setRowPaddingEnd(int rowPaddingEnd) {
			this.rowPaddingEnd = rowPaddingEnd;
			return this;
		}

		private int rowleftpadding;
		private int showDividers;

		public Builder setRowleftpadding(int rowleftpadding) {
			this.rowleftpadding = rowleftpadding;
			return this;
		}

		public Builder setNormalLineColorId(int normalLineColorId) {
			this.normalLineColorId = normalLineColorId;
			return this;
		}

		@Deprecated
		/**
		 * 使用 setDividerResId(int dividerResId)替代
		 * @param rowStyle
		 * @return
		 */
		public Builder setRowStyle(int rowStyle) {
			this.rowStyle = rowStyle;
			return this;
		}

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

		public Builder setOut_circle_Size(int out_circle_Size) {
			this.out_circle_Size = out_circle_Size;
			return this;
		}

		public Builder setLinewidth(int linewidth) {
			this.linewidth = linewidth;
			return this;
		}

		/**
		 * Set how dividers should be shown between items in this layout
		 * 
		 * Parameters: showDividers One or more of SHOW_DIVIDER_BEGINNING, SHOW_DIVIDER_MIDDLE, or SHOW_DIVIDER_END, or SHOW_DIVIDER_NONE to show no dividers.
		 * 
		 * @param showDividers
		 * @return
		 */
		public Builder setShowDividers(int showDividers) {
			this.showDividers = showDividers;
			return this;
		}

		public DisplayOptions build() {
			return new DisplayOptions(this);
		}
	}
}
