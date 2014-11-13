package com.kubeiwu.commontool.view.util;

import android.content.Context;

public interface ParaSetting {
	Para<Boolean> xiazshud1 = new Para<Boolean>("key1", true);
	Para<Boolean> xiazshud2 = new Para<Boolean>("key2", true);
	Para<Boolean> xiazshud3 = new Para<Boolean>("key3", true);
	Para<Boolean> xiazshud4 = new Para<Boolean>("key4", true);
	Para<Boolean> xiazshud5 = new Para<Boolean>("key5", true);
	Para<Boolean> xiazshud6 = new Para<Boolean>("key61", true);
	Para<Integer> xiazshud7 = new Para<Integer>("key7", 10);
	Para<String> xiazshud8 = new Para<String>(null, "haha");
	Para<String> xiazshud9 = new Para<String>(null, "dd");

	public class ParaUtil {


		public static void initPrar(Context context) {
			SettingsUtil settingsUtil = new SettingsUtil(context);

			settingsUtil.initParaFromPreferences(xiazshud1);
			settingsUtil.initParaFromPreferences(xiazshud2);
			settingsUtil.initParaFromPreferences(xiazshud3);
			settingsUtil.initParaFromPreferences(xiazshud4);
			settingsUtil.initParaFromPreferences(xiazshud5);
			settingsUtil.initParaFromPreferences(xiazshud6);
			settingsUtil.initParaFromPreferences(xiazshud7);
			settingsUtil.initParaFromPreferences(xiazshud8);
			settingsUtil.initParaFromPreferences(xiazshud9);
		}
	}
}
