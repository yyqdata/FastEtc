package com.haomai.yyq.yyq.app.net;

import com.haomai.yyq.yyq.app.ConfigType;
import com.haomai.yyq.yyq.app.YyqApp;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 懒汉单例模式
 * Created by YYQ on 2017/12/6.
 */

public class RestCreator {

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    public static WeakHashMap<String,Object> getRestParams(){
        return RestParamHolder.PARAMS;
    }

    private static class RestParamHolder{
        private static final WeakHashMap<String,Object> PARAMS = new WeakHashMap<>();
    }

    private static class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }


    private static class RetrofitHolder {

        private static final String BASE_URL = (String) YyqApp.getCongurations().get(ConfigType.API_HOST.name());

        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(OkhttpHolder.OKHTTP_CLIENT)
                .build();

    }

    private static class OkhttpHolder {

        private static final int TIME_OUT = 60;
        private static final OkHttpClient OKHTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }
}
