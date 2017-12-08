package com.haomai.yyq.fastetc;

import android.app.Application;

import com.haomai.yyq.yyq.app.YyqApp;
import com.haomai.yyq.yyq.ec.icon.ECFontModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by YYQ on 2017/12/4.
 */

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        YyqApp.init(this)
                .withIcon(new FontAwesomeModule())
                .withIcon(new ECFontModule())
                .withApiHost("http://127.0.0.1")
                .configure();

    }
}
