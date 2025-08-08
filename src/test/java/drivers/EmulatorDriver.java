package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.LocalConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class EmulatorDriver implements WebDriverProvider {

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        LocalConfig config = ConfigFactory.create(LocalConfig.class);

        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(config.getPlatformName());
        options.setDeviceName(config.getDeviceName());
        options.setApp(config.getApp());
        options.setAppPackage(config.getAppPackage());
        options.setAppActivity(config.getAppActivity());
        options.setAutomationName("UiAutomator2");

        try {
            return new AndroidDriver(new URL(config.getServerURL()), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium server URL", e);
        }
    }
}