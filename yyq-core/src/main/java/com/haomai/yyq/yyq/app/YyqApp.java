package com.haomai.yyq.yyq.app;

import android.content.Context;

import java.io.Externalizable;
import java.util.HashMap;

/**
 * Created by YYQ on 2017/12/1.
 */

public final class YyqApp{

    /**
     * 初始化APP
     *
     * @param context
     * @return
     */
    public static Configurator init(Context context) {
        getCongurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getCongurations() {
        return Configurator.getInstance().getYyqConfigs();
    }

    public static Context getApplicatonContext(){
        return (Context) getCongurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
