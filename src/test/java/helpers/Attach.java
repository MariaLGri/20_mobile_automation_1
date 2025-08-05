package helpers;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;

public class Attach {

    @Attachment(value = "Скриншот", type = "image/png")
    public static byte[] attachScreenshot(AndroidDriver driver) {
        return driver.getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Исходный код", type = "text/plain")
    public static String attachPageSource(AndroidDriver driver) {
        return driver.getPageSource();
    }

    @Attachment(value = "Логи устройства", type = "text/plain")
    public static String attachLogs(String message) {
        return message;
    }

    @Attachment(value = "Видео", type = "text/html", fileExtension = ".html")
    public static String attachVideoLink(String sessionId) {
        return "<html><body><video width='100%' height='100%' controls>" +
                "<source src='" + Browserstack.videoUrl(sessionId) +
                "' type='video/mp4'></video></body></html>";
    }
}