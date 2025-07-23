package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    private static final BrowserStackConfig browserStackConfig =
            ConfigFactory.create(BrowserStackConfig.class, System.getProperties());

    public static BrowserStackConfig getBrowserStackConfig() {
        return browserStackConfig;
    }
}