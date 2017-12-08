package com.haomai.yyq.fastetc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.haomai.yyq.yyq.app.YyqApp;
import com.haomai.yyq.yyq.app.delegates.YyqDelegate;
import com.haomai.yyq.yyq.app.net.RestClient;
import com.haomai.yyq.yyq.app.net.callback.IError;
import com.haomai.yyq.yyq.app.net.callback.IFailure;
import com.haomai.yyq.yyq.app.net.callback.IRequest;
import com.haomai.yyq.yyq.app.net.callback.ISuccess;
import com.haomai.yyq.yyq.app.ui.LoaderStyle;

/**
 * Created by YYQ on 2017/12/5.
 */

public class ExampleDelegate extends YyqDelegate {


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    public void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .load(getContext(), LoaderStyle.BallBeatIndicator)
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
//                        Toast.makeText(YyqApp.getApplicatonContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        Toast.makeText(YyqApp.getApplicatonContext(), "网络请求开始啦！", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onRequestEnd() {
//                        Toast.makeText(YyqApp.getApplicatonContext(), "网络请求结束啦！", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();

    }
}
