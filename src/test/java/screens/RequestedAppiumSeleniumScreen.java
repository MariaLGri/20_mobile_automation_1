package screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.appium.java_client.AppiumBy.className;

public class RequestedAppiumSeleniumScreen {

    private final SelenideElement backButton = $(className("android.widget.ImageButton"));
    private final ElementsCollection searchResults = $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));

    private final ElementsCollection textElements = $$(className("android.widget.TextView"));

    @Step("Открываем первую статью")
    public RequestedAppiumSeleniumScreen openFirstSearchResult() {
        searchResults.first().click();
        return this;
    }

    @Step("Проверяем заголовок статьи")
    public void shouldSeeArticleTitle(String expectedTitle) {
        textElements.findBy(text(expectedTitle))
                .shouldBe(visible);
    }

    @Step("Нажать кнопку назад")
    public void clickBackButton() {
        backButton.click();

    }

    public boolean isAppClosed() {
        try {
            AndroidDriver driver = (AndroidDriver) WebDriverRunner.getWebDriver();
            String currentActivity = ((AndroidDriver) getWebDriver()).currentActivity();
            ApplicationState appState = driver.queryAppState("org.wikipedia.alpha");

            return appState != ApplicationState.RUNNING_IN_FOREGROUND
                    || !currentActivity.startsWith("org.wikipedia.alpha");
        } catch (Exception e) {
            return true;
        }
    }

}
