package com.kubeiwu.commontool.view.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SettingsUtil {
	public static String getStringValue(Context context,String key) {
		SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(context);
		String value=sharedPreferences.getString(key, "");
		
		return value;
	}

	public static int getIntValue(String key) {
		
		return 1;
	}

	public static boolean getBooleanValue(String key) {
		
		return false;
	}

}
