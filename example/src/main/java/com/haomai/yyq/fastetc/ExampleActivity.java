package com.haomai.yyq.fastetc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.haomai.yyq.yyq.app.YyqApp;
import com.haomai.yyq.yyq.app.activities.ProxyActivity;
import com.haomai.yyq.yyq.app.delegates.YyqDelegate;
import com.haomai.yyq.yyq.ec.icon.ECFontModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class ExampleActivity extends ProxyActivity {


    @Override
    public YyqDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
