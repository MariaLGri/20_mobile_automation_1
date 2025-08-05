package pages;


import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;

public class SearchMainPage {
    public SearchMainPage clickSearchIcon() {
        $(accessibilityId("Search Wikipedia")).click();
        return this;
    }

}
