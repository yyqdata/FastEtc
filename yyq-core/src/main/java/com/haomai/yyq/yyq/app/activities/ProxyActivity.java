package com.haomai.yyq.yyq.app.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

import com.haomai.yyq.yyq.R;
import com.haomai.yyq.yyq.app.delegates.YyqDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by YYQ on 2017/12/5.
 */

public abstract class ProxyActivity extends SupportActivity {

    public abstract YyqDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.gc();
        System.runFinalization();
    }
}
