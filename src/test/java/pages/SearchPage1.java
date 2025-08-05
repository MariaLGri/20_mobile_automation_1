package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.appium.java_client.AppiumBy.className;

public class SearchPage1 {
    private final SelenideElement searchInput = $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));
    private final SelenideElement backButton = $(className("android.widget.ImageButton"));
    private final ElementsCollection searchResults = $$(AppiumBy.id("org.wikipedia.alpha:id/page_list_item_title"));
    private final ElementsCollection textElements = $$(className("android.widget.TextView"));


    public void enterSearchQuery(String query) {
        searchInput.sendKeys(query);

    }

    public void shouldSeeResults() {
        textElements.shouldHave(sizeGreaterThan(0));
    }

    public SearchPage1 openFirstSearchResult() {
        searchResults.first().click();
        return this;
    }

    public void shouldSeeArticleTitle(String expectedTitle) {
        textElements.findBy(text(expectedTitle))
                .shouldBe(visible);
    }

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
