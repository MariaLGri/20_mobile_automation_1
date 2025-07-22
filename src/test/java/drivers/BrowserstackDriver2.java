package drivers;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;


import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;


import org.openqa.selenium.remote.DesiredCapabilities;


public class BrowserstackDriver2 implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();

        // Обязательные параметры авторизации
        caps.setCapability("browserstack.user", "panchmaria_ygdXiH");
        caps.setCapability("browserstack.key", "66sFgUe7JzauwdSYbky4");

        // Основные параметры устройства
        caps.setCapability("platformName", "Android");
        caps.setCapability("deviceName", "Google Pixel 7");
        caps.setCapability("platformVersion", "13.0");
        caps.setCapability("automationName", "UiAutomator2");

        // URL тестируемого приложения
        caps.setCapability("app", "bs://sample-app");

        // Дополнительные параметры BrowserStack
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

//        String browserstackUrl = String.format(
//                "https://%s:%s@hub-cloud.browserstack.com/wd/hub",
//                "panchmaria_ygdXiH",
//                "66sFgUe7JzauwdSYbky4"
//        );

        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}