package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:${env}.properties"
})
public interface LocalConfig extends Config {

    @Key("emulator.platformName")
    String getPlatformName();

    @Key("emulator.deviceName")
    String getDeviceName();

    @Key("emulator.app")
    String getApp();

    @Key("emulator.appPackage")
    String getAppPackage();

    @Key("emulator.appActivity")
    String getAppActivity();

    @Key("emulator.serverURL")
    String getServerURL();
}