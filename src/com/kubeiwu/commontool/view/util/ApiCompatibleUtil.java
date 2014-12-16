package com.kubeiwu.commontool.view.util;

import android.graphics.drawable.Drawable;
import android.view.View;

public class ApiCompatibleUtil {
	public static void setViewBackground(View view,Drawable background){
		if(android.os.Build.VERSION.SDK_INT<=16){
			view.setBackgroundDrawable(background);
		}else{
			view.setBackground(background);
		}
	}
}
