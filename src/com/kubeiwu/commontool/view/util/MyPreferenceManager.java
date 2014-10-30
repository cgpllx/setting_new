package com.kubeiwu.commontool.view.util;

import android.content.Context;
import android.preference.PreferenceManager;

public class MyPreferenceManager {

	public static <T> Void getValue(Class<T> clazz, Context context, String key) {
//		T t;
		if (clazz == Boolean.class || clazz == boolean.class) {
		  PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
		} else if (clazz == Integer.class || clazz == int.class) {
			PreferenceManager.getDefaultSharedPreferences(context).getInt(key, 1);
		}

		return null;
	}
}
