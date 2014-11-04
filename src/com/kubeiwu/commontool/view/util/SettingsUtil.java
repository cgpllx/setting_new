package com.kubeiwu.commontool.view.util;

import java.util.Set;

import android.content.SharedPreferences;

public class SettingsUtil {

	@SuppressWarnings("unchecked")
	public static <T> void initParaFromPreferences(Para<T> para, SharedPreferences preferences) {
		if (!preferences.contains(para.key)) {
			System.out.println("空吗="+para.key);
			return;
		}
		Class<T> clazz = (Class<T>) para.value.getClass();
		if (clazz == String.class) {
			para.value = (T) preferences.getString(para.key, (String) para.value);
		} else if (clazz == int.class || clazz == Integer.class) {
			Integer i = preferences.getInt(para.key, (Integer) para.value);
			para.value = (T) i;
		} else if (clazz == boolean.class || clazz == Boolean.class) {
			Boolean b = preferences.getBoolean(para.key, (Boolean) para.value);
			para.value = (T) b;
		} else if (clazz == float.class || clazz == Float.class) {
			Float f = preferences.getFloat(para.key, (Float) para.value);
			para.value = (T) f;
		} else if (clazz == long.class || clazz == Long.class) {
			Long f = preferences.getLong(para.key, (Long) para.value);
			para.value = (T) f;
		} else if (clazz == Set.class) {
			Set<String> s = preferences.getStringSet(para.key, (Set<String>) para.value);
			para.value = (T) s;
		}
	}
}
