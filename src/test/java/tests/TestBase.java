
package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import drivers.BrowserstackDriver;
import helpers.Attach;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browser = BrowserstackDriver.class.getName();
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
        String sessionId = null;
        try {
            // Проверяем, что драйвер запущен
            if (WebDriverRunner.hasWebDriverStarted()) {
                // Получаем sessionId перед любыми другими операциями
                sessionId = Selenide.sessionId().toString();
                System.out.println("Session ID: " + sessionId);

                // Безопасное приведение типа для AndroidDriver
                WebDriver driver = WebDriverRunner.getWebDriver();
                if (driver instanceof AndroidDriver) {
                    AndroidDriver androidDriver = (AndroidDriver) driver;

                    // Добавляем скриншот
                    Attach.attachScreenshot(androidDriver);

                    // Добавляем исходный код страницы
                    Attach.attachPageSource(androidDriver);

                    // Добавляем логи с информацией об устройстве
                    String deviceName = String.valueOf(androidDriver.getCapabilities()
                            .getCapability("deviceName"));
                    Attach.attachLogs("Test finished on device: " + deviceName);
                } else {
                    Attach.attachLogs("Warning: Driver is not AndroidDriver");
                }
            }
        } catch (Exception e) {
            System.err.println("Failed to capture attachments: " + e.getMessage());
        } finally {
            try {
                // Добавляем ссылку на видео, если sessionId был получен
                if (sessionId != null) {
                    Attach.attachVideoLink(sessionId);
                }
            } catch (Exception e) {
                System.err.println("Failed to attach video link: " + e.getMessage());
            }

            // Всегда закрываем драйвер
            if (WebDriverRunner.hasWebDriverStarted()) {
                closeWebDriver();
            }
        }
    }
}
