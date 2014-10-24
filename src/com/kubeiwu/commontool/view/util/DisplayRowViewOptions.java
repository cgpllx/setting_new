package com.kubeiwu.commontool.view.util;

/**
 * 选择器的参数
 * 
 * @author Administrator
 */
public class DisplayRowViewOptions {
	private int normalLineColorId = android.R.color.darker_gray;// 线条颜色资源id FFD6D6D6
	private int normalBackgroundColorId = android.R.color.white;// 默认时候的背景
	private int pressedLineColorId = android.R.color.darker_gray;// 按下时候的线条颜色id
	private int pressedBackgroundColorId = android.R.color.holo_blue_light;// 按下时候的背景颜色资源id
	private int out_circle_Size = 15; // 圆角大小
	private int linewidth = 1;// 线宽

	private int titleColorId = android.R.color.black; // 字体颜色 
	private int titleSizePx = 16; // title 字体大小 px

	public int getTitleColorId() {
		return titleColorId;
	}

	public void setTitleColorId(int titleColorId) {
		this.titleColorId = titleColorId;
	}

	public int getTitleSizePx() {
		return titleSizePx;
	}

	public void setTitleSizePx(int titleSizePx) {
		this.titleSizePx = titleSizePx;
	}

	public int getNormalLineColorId() {
		return normalLineColorId;
	}

	public DisplayRowViewOptions() {
		super();
	}

	public void setNormalLineColorId(int normalLineColorId) {
		this.normalLineColorId = normalLineColorId;
	}

	public int getNormalBackgroundColorId() {
		return normalBackgroundColorId;
	}

	public DisplayRowViewOptions(int normalLineColorId, int normalBackgroundColorId, int pressedLineColorId, int pressedBackgroundColorId, int out_circle_Size, int linewidth) {
		super();
		this.normalLineColorId = normalLineColorId;
		this.normalBackgroundColorId = normalBackgroundColorId;
		this.pressedLineColorId = pressedLineColorId;
		this.pressedBackgroundColorId = pressedBackgroundColorId;
		this.out_circle_Size = out_circle_Size;
		this.linewidth = linewidth;
	}

	public void setNormalBackgroundColorId(int normalBackgroundColorId) {
		this.normalBackgroundColorId = normalBackgroundColorId;
	}

	public int getPressedLineColorId() {
		return pressedLineColorId;
	}

	public void setPressedLineColorId(int pressedLineColorId) {
		this.pressedLineColorId = pressedLineColorId;
	}

	public int getPressedBackgroundColorId() {
		return pressedBackgroundColorId;
	}

	public void setPressedBackgroundColorId(int pressedBackgroundColorId) {
		this.pressedBackgroundColorId = pressedBackgroundColorId;
	}

	public int getOut_circle_Size() {
		return out_circle_Size;
	}

	public void setOut_circle_Size(int out_circle_Size) {
		this.out_circle_Size = out_circle_Size;
	}

	public int getLinewidth() {
		return linewidth;
	}

	public void setLinewidth(int linewidth) {
		this.linewidth = linewidth;
	}
}
