package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.BrowserstackConfig;
import config.ConfigProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    private static final BrowserstackConfig config = ConfigProvider.getBrowserstackConfig();

    @Override
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        caps.setCapability("app", config.app());
        caps.setCapability("deviceName", config.deviceName());
        caps.setCapability("platformName", config.platformName());
        caps.setCapability("platformVersion", config.platformVersion());
        caps.setCapability("browserstackLocal", true);

        MutableCapabilities bstackOptions = new MutableCapabilities();
        bstackOptions.setCapability("browserstack.userName", config.userName());
        bstackOptions.setCapability("browserstack.accessKey", config.accessKey());
        bstackOptions.setCapability("projectName", config.projectName());
        bstackOptions.setCapability("buildName", config.buildName());
        bstackOptions.setCapability("sessionName", config.sessionName());
        bstackOptions.setCapability("video", true);

        caps.setCapability("bstack:options", bstackOptions);
//
//        try {
//            return new AndroidDriver(new URL(config.remoteUrl()), caps);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("Invalid URL for BrowserStack", e);
//        }
//    }
//}
        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}