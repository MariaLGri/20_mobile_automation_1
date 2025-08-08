package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    private static final BrowserStackConfig browserStackConfig =
            ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    private static final LocalConfig localConfig =
            ConfigFactory.create(LocalConfig.class, System.getProperties());

    public static BrowserStackConfig getBrowserStackConfig() {
        return browserStackConfig;
    }

    public static LocalConfig getLocalConfig() {
        return localConfig;
    }
}