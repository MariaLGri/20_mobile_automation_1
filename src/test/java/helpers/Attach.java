package helpers;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Attach {
        @Attachment(value = "Screenshot", type = "image/png")
        public static byte[] attachScreenshot(AndroidDriver driver) {
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        }

        @Attachment(value = "Page Source", type = "text/xml")
        public static String attachPageSource(AndroidDriver driver) {
            return driver.getPageSource();
        }

        @Attachment(value = "Device Logs", type = "text/plain")
        public static String attachLogs(String logs) {
            return logs;
        }

        @Attachment(value = "Video", type = "text/html", fileExtension = ".html")
        public static String attachVideoLink(String sessionId) {
            return "<html><body><video width='100%' height='100%' controls autoplay><source src='"
                    + Browserstack.videoUrl(sessionId)
                    + "' type='video/mp4'></video></body></html>";
        }

}
