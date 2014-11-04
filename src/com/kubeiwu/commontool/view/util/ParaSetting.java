package com.kubeiwu.commontool.view.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public interface ParaSetting {
	Para<Boolean> xiazshud1 = new Para<Boolean>("key1", true);
	Para<Boolean> xiazshud2 = new Para<Boolean>("key2", true);
	Para<Boolean> xiazshud3 = new Para<Boolean>("key3", true);
	Para<Boolean> xiazshud4 = new Para<Boolean>("key4", true);
	Para<Boolean> xiazshud5 = new Para<Boolean>("key5", true);
	Para<Boolean> xiazshud6 = new Para<Boolean>("key61", true);
	Para<Integer> xiazshud7 = new Para<Integer>("key7", 10);
	Para<String> xiazshud8 = new Para<String>(null, "haha");

	public class ParaUtil {
		private SharedPreferences preferences;

		public ParaUtil(Context context) {
			preferences = PreferenceManager.getDefaultSharedPreferences(context);
		}

		public void initPrar() {
			SettingsUtil.initParaFromPreferences(xiazshud1, preferences);
			SettingsUtil.initParaFromPreferences(xiazshud2, preferences);
			SettingsUtil.initParaFromPreferences(xiazshud3, preferences);
			SettingsUtil.initParaFromPreferences(xiazshud4, preferences);
			SettingsUtil.initParaFromPreferences(xiazshud5, preferences);
			SettingsUtil.initParaFromPreferences(xiazshud6, preferences);
			SettingsUtil.initParaFromPreferences(xiazshud7, preferences);
			SettingsUtil.initParaFromPreferences(xiazshud8, preferences);
		}
	}
}
