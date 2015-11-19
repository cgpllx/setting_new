package com.kubeiwu.commontool.view.core;

public class IRowStyleFactory {
	/**
	 * 
	 * @param circleSize
	 *            圆角大小
	 * @return
	 */
	public static IRowStyle getAllAroundStyle(int circleSize) {
		return new AllAroundStyle(circleSize);
	}

	/**
	 * 
	 * 
	 * @param middleLinePaddingLeft
	 *            中间线条左边距
	 * @return
	 */
	public static IRowStyle getUpDownAroundStyle(int middleLinePaddingLeft) {
		return new UpDownAroundStyle(middleLinePaddingLeft);
	}
}
