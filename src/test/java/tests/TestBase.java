
package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import drivers.EmulatorDriver;
import helpers.Attach;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;


import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static config.Environment.BROWSERSTACK;
import static config.Environment.EMULATOR;

public class TestBase {
    private static final String env = System.getProperty("env", "emulator");

    @BeforeAll
    static void beforeAll() {

        if (env.equals(BROWSERSTACK.getDisplayName())) {
            Configuration.browser = BrowserstackDriver.class.getName();
        } else if (env.equals(EMULATOR.getDisplayName())) {
            Configuration.browser = EmulatorDriver.class.getName();
        } else {
            throw new IllegalArgumentException("Unsupported env: " + env);
        }
        Configuration.browserSize = null;
        Configuration.timeout = 30000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }


    @AfterEach
    void addAttachments() {
        try {
            AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
            Attach.attachScreenshot(driver);
            Attach.attachPageSource(driver);
            String deviceName = driver.getCapabilities().getCapability("deviceName").toString();
            Attach.attachLogs("Тест выполнен на устройстве: " + deviceName);
            String sessionId = Selenide.sessionId().toString();
            Attach.attachVideoLink(sessionId);

        } catch (Exception e) {
            System.out.println("Не удалось добавить вложения: " + e.getMessage());
        } finally {
            closeWebDriver();
        }
    }
}