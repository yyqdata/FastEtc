package com.haomai.yyq.yyq.app.ui;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.haomai.yyq.yyq.R;
import com.haomai.yyq.yyq.app.util.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * 动画加载类
 * <p>
 * Created by YYQ on 2017/12/6.
 */

public class YyqLoader {

    private static final int LOAD_SCALE = 8;

    private static final ArrayList<AppCompatDialog> LODERS = new ArrayList<>();

    public static final LoaderStyle DEFAULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator;

    public static final void showLoading(Context context, String type) {
        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.MyDialog);
        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.creat(context, type);
        dialog.setContentView(avLoadingIndicatorView);
        int screenWidth = DimenUtil.getScreenWidth();
        int screenHeight = DimenUtil.getScreenHeight();

        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = screenWidth / LOAD_SCALE;
            lp.height = screenHeight / LOAD_SCALE;

            lp.gravity = Gravity.CENTER;
        }

        LODERS.add(dialog);
        dialog.show();

    }

    public static final void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static final void showLoading(Context context, Enum<LoaderStyle> styleEnum) {
        showLoading(context, styleEnum.name());
    }

    public static final void stopLoading() {
        for (AppCompatDialog dialog :
                LODERS) {
            if (dialog != null) {
                dialog.cancel();
            }
        }
    }

}
