package com.haomai.yyq.yyq.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 配置信息存储表
 * 使用静态内部类的线程安全的单例模式
 * <p>
 * Created by YYQ on 2017/12/1.
 */

public class Configurator {

    private static final HashMap<String, Object> YYQ_CONFIGS = new HashMap<>();
    private static final List<IconFontDescriptor> ICONS = new ArrayList<>();

    private Configurator() {
        YYQ_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    static Configurator getInstance() {
        return Holder.CONFIG_INSTANCE;
    }

    private static class Holder {
        private static final Configurator CONFIG_INSTANCE = new Configurator();
    }

    HashMap<String, Object> getYyqConfigs() {
        return YYQ_CONFIGS;
    }

    public void configure() {
        initIcons();
        YYQ_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    private void initIcons(){
        if (ICONS.size()>0){
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withApiHost(String host) {
        YYQ_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor iconFontDescriptor){
        ICONS.add(iconFontDescriptor);
        return this;
    }

    private void checkConfigurator(){
        final boolean isReady = (boolean) YYQ_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady){
            throw new RuntimeException("Configuration is not ready! Call configure()!");
        }
    }

    @SuppressWarnings("unchecked")
    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfigurator();
        return (T) YYQ_CONFIGS.get(key.name());
    }

}
