package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:config/${env}.properties",
        "classpath:config/remote.properties"
})
public interface BrowserstackConfig extends Config {

    @Key("browserstack.user")
    @DefaultValue("${BROWSERSTACK_USERNAME}")
    String userName();

    @Key("browserstack.key")
    @DefaultValue("${BROWSERSTACK_ACCESS_KEY}")
    String accessKey();

    @Key("app")
    String app();

    @Key("deviceName")
    @DefaultValue("Google Pixel 6")
    String deviceName();

    @Key("platformName")
    String platformName();

    @Key("platformVersion")
    String platformVersion();

    @Key("projectName")
    String projectName();

    @Key("buildName")
    String buildName();

    @Key("sessionName")
    String sessionName();

    @Key("remoteUrl")
    @DefaultValue("https://hub.browserstack.com/wd/hub")
    String remoteUrl();
}