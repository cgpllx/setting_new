package com.kubeiwu.commontool.view.util;

import android.util.SparseArray;

public class KSparseArray<E> extends SparseArray<E> {
	
	public KSparseArray<E> putValue(int key, E value) {
		super.put(key, value);
		return this;
	}
}
