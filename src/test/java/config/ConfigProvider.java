package config;

import lombok.Getter;
import org.aeonbits.owner.ConfigFactory;

@Getter
public class ConfigProvider {
    private static final BrowserstackConfig browserstackConfig =
            ConfigFactory.create(BrowserstackConfig.class, System.getProperties());

    public static BrowserstackConfig getBrowserstackConfig() {
        return browserstackConfig;
    }
}