package com.haomai.yyq.yyq.app.net;

import android.content.Context;

import com.haomai.yyq.yyq.app.net.callback.IError;
import com.haomai.yyq.yyq.app.net.callback.IFailure;
import com.haomai.yyq.yyq.app.net.callback.IRequest;
import com.haomai.yyq.yyq.app.net.callback.ISuccess;
import com.haomai.yyq.yyq.app.net.callback.RequestCallbacks;
import com.haomai.yyq.yyq.app.ui.LoaderStyle;
import com.haomai.yyq.yyq.app.ui.YyqLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 使用建造者模式
 * Created by YYQ on 2017/12/5.
 */

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getRestParams();
    private final IRequest IREQUEST;
    private final IError IERROR;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final RequestBody BODY;
    private final File FILE;
    private final Context CONTEXT;
    private final LoaderStyle LOADER_STYLE;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest iRequest,
                      IError iError,
                      ISuccess iSuccess,
                      IFailure iFailure,
                      RequestBody BODY,
                      File file,
                      Context context,
                      LoaderStyle loaderStyle) {
        this.URL = url;
        PARAMS.putAll(params);
        this.IREQUEST = iRequest;
        this.IERROR = iError;
        this.ISUCCESS = iSuccess;
        this.IFAILURE = iFailure;
        this.BODY = BODY;
        this.FILE = file;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethods methods) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }
        if (LOADER_STYLE != null) {
            YyqLoader.showLoading(CONTEXT, LOADER_STYLE);
        }
        switch (methods) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.put_raw(URL, BODY);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.post_raw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        MultipartBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part part =
                        MultipartBody.Part.create(requestBody);
                call = service.upload(URL, part);
                break;
            default:
                break;
        }
        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(IREQUEST, IERROR, ISUCCESS, IFAILURE, CONTEXT, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethods.GET);
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethods.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("param must be null!");
            }
            request(HttpMethods.PUT_RAW);
        }
    }

    public final void post() {
        if (BODY == null) {
            request(HttpMethods.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("param must be null!");
            }
            request(HttpMethods.POST_RAW);
        }
    }

    public final void upload() {
        request(HttpMethods.UPLOAD);
    }

    public final void delete() {
        request(HttpMethods.DELETE);
    }


}
