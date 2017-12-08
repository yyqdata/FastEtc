package com.haomai.yyq.yyq.app.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * Created by YYQ on 2017/12/6.
 */

public class LoaderCreator {
    private static final WeakHashMap<String,Indicator> LOADERING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView creat(Context context,String type){
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        if (LOADERING_MAP.get(type)==null){
            final Indicator indicator = getIndicator(type);
            LOADERING_MAP.put(type,indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADERING_MAP.get(type));
        return avLoadingIndicatorView;
    }

    private static Indicator getIndicator(String type){
        if (type==null||type.isEmpty()){
            return null;
        }

        final StringBuilder drawableClassName = new StringBuilder();
        if (!type.contains(".")){
            final String packgeName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(packgeName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(type);
        try {
            Class drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
