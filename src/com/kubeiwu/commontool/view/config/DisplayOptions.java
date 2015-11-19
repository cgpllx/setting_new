package com.kubeiwu.commontool.view.config;

import android.util.TypedValue;

import com.kubeiwu.commontool.view.core.IRowStyle;

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
	private int linewidth;

	private int rowTitleColorId;
	private int rowTitleSizePx;

	private int groupTitleColorId;
	private int groupTitleSize;

	private int rowPaddingStart;

	private IRowStyle iRowStyle;
	private int unit;

	public int getUnit() {
		return unit;
	}

	public IRowStyle getiRowStyle() {
		return iRowStyle;
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

	private int dividerPadding;

	public int getDividerPadding() {
		return dividerPadding;
	}

	public int getGroupTitleColorId() {
		return groupTitleColorId;
	}

	public int getGroupTitleSize() {
		return groupTitleSize;
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

	public int getLinewidth() {
		return linewidth;
	}

	private DisplayOptions(Builder builder) {
		this.normalLineColorId = builder.normalLineColorId;
		this.normalBackgroundColorId = builder.normalBackgroundColorId;
		this.pressedLineColorId = builder.pressedLineColorId;
		this.pressedBackgroundColorId = builder.pressedBackgroundColorId;
		this.linewidth = builder.linewidth;

		this.rowTitleColorId = builder.rowTitleColorId;
		this.rowTitleSizePx = builder.rowTitleSize;

		this.groupTitleColorId = builder.groupTitleColorId;
		this.groupTitleSize = builder.groupTitleSize;
		this.rowPaddingStart = builder.rowPaddingStart;
		this.rowPaddingEnd = builder.rowPaddingEnd;
		this.dividerPadding = builder.dividerPadding;
		this.unit = builder.unit;

		this.iRowStyle = builder.iRowStyle;
	}

	public static DisplayOptions createsimpleDisplayOptions() {
		return new Builder().build();
	}

	public static class Builder {

		private int rowPaddingEnd;
		private int rowPaddingStart;

		private int normalLineColorId = android.R.color.darker_gray;// 线条颜色资源id FFD6D6D6
		private int normalBackgroundColorId = android.R.color.white;// 默认时候的背景
		private int pressedLineColorId = android.R.color.darker_gray;// 按下时候的线条颜色id
		private int pressedBackgroundColorId = android.R.color.holo_blue_light;// 按下时候的背景颜色资源id

		private int linewidth = 1;// 线宽

		private int rowTitleColorId = android.R.color.black; // 行的title字体颜色
		private int rowTitleSize = 16; // title 字体大小 px

		private int groupTitleColorId = android.R.color.black; // 组的title字体颜色
		private int groupTitleSize = 16; // title 字体大小 px

		private int dividerPadding = 0;
		private IRowStyle iRowStyle;
		private int unit = TypedValue.COMPLEX_UNIT_SP;

		/**
		 * Set the default text size to a given unit and value. See {@link TypedValue} for the possible dimension units.
		 * 
		 * @param unit
		 *            The desired dimension unit.
		 * @param size
		 *            The desired size in the given units.
		 */
		public void setTextUnit(int unit) {
			this.unit = unit;
		}

		public Builder setRowStyle(IRowStyle iRowStyle) {
			this.iRowStyle = iRowStyle;
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

		public Builder setRowPaddingStart(int rowPaddingStart) {
			this.rowPaddingStart = rowPaddingStart;
			return this;
		}

		public Builder setNormalLineColorId(int normalLineColorId) {
			this.normalLineColorId = normalLineColorId;
			return this;
		}

		public Builder setRowTitleColorId(int rowTitleColorId) {
			this.rowTitleColorId = rowTitleColorId;
			return this;
		}

		public Builder setRowTitleSize(int rowTitleSize) {
			this.rowTitleSize = rowTitleSize;
			return this;
		}

		public Builder setGroupTitleColorId(int groupTitleColorId) {
			this.groupTitleColorId = groupTitleColorId;
			return this;
		}

		public Builder setGroupTitleSize(int groupTitleSize) {
			this.groupTitleSize = groupTitleSize;
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

		public Builder setLinewidth(int linewidth) {
			this.linewidth = linewidth;
			return this;
		}

		public DisplayOptions build() {
			return new DisplayOptions(this);
		}
	}
}
