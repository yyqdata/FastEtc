package com.haomai.yyq.yyq.app.net.callback;

import android.content.Context;
import android.os.Handler;

import com.haomai.yyq.yyq.app.ui.LoaderStyle;
import com.haomai.yyq.yyq.app.ui.YyqLoader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YYQ on 2017/12/6.
 */

public class RequestCallbacks implements Callback<String> {

    private final IRequest IREQUEST;
    private final IError IERROR;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;
    private static final Handler hander = new Handler();

    public RequestCallbacks(IRequest iRequest, IError iError, ISuccess iSuccess, IFailure iFailure
            , Context context, LoaderStyle loaderStyle) {
        this.IREQUEST = iRequest;
        this.IERROR = iError;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (ISUCCESS != null) {
                    ISUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (IERROR != null) {
                IERROR.onError(response.code(), response.message());
            }
        }
        requestEnd();
        stopLoading();
    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null) {
            IFAILURE.onFailure();
        }
        requestEnd();
        stopLoading();
    }

    private void requestEnd() {
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
    }

    private void stopLoading() {
        if (LOADER_STYLE!=null){
            hander.postDelayed(new Runnable() {
                @Override
                public void run() {
                    YyqLoader.stopLoading();
                }
            },1000);
        }
    }
}
