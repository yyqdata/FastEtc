package com.haomai.yyq.yyq.app.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.haomai.yyq.yyq.app.YyqApp;

/**
 * Created by YYQ on 2017/12/6.
 */

public class DimenUtil {

    public static final int getScreenWidth(){
        final Resources resources = YyqApp.getApplicatonContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static final int getScreenHeight(){
        final Resources resources = YyqApp.getApplicatonContext().getResources();
        final DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return displayMetrics.heightPixels;
    }
}
