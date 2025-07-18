package config;

import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {
    private static final BrowserstackConfig browserstackConfig =
            ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    public static BrowserstackConfig getBrowserstackConfig() {

        return browserstackConfig;
    }
}
