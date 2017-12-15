package com.haomai.yyq.fastetc;

import com.haomai.yyq.yyq.app.activities.ProxyActivity;
import com.haomai.yyq.yyq.app.delegates.YyqDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public YyqDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
