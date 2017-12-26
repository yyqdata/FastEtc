package com.haomai.yyq.yyq.app.net;

import android.content.Context;

import com.haomai.yyq.yyq.app.net.callback.IError;
import com.haomai.yyq.yyq.app.net.callback.IFailure;
import com.haomai.yyq.yyq.app.net.callback.IRequest;
import com.haomai.yyq.yyq.app.net.callback.ISuccess;
import com.haomai.yyq.yyq.app.ui.LoaderCreator;
import com.haomai.yyq.yyq.app.ui.LoaderStyle;
import com.haomai.yyq.yyq.app.ui.YyqLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 建造者
 * Created by YYQ on 2017/12/6.
 */

public class RestClientBuilder {

    private String url;
    private static final Map<String, Object> PARAMS = RestCreator.getRestParams();
    private IRequest iRequest;
    private IError iError;
    private ISuccess iSuccess;
    private IFailure iFailure;
    private RequestBody body;
    private File file;
    private Context context;
    private LoaderStyle loaderStyle;

    RestClientBuilder() {
    }


    public final RestClientBuilder url(String url) {
        this.url = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.iRequest = iRequest;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.iError = iError;
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.iSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.iFailure = iFailure;
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.file = file;
        return this;
    }

    public final RestClientBuilder file(String filePath) {
        this.file = new File(filePath);
        return this;
    }


    public final RestClientBuilder load(Context context, LoaderStyle style) {
        this.context = context;
        this.loaderStyle = style;
        return this;
    }

    public final RestClientBuilder load(Context context) {
        load(context, YyqLoader.DEFAULT_LOADER);
        return this;
    }

    public final RestClient build() {
        return new RestClient(url, PARAMS, iRequest, iError, iSuccess, iFailure, body, file, context, loaderStyle);
    }
}
