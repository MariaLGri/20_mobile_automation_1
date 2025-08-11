package screens;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.className;

public class MainWikiScreen {

    private final SelenideElement searchInput = $(AppiumBy.id("org.wikipedia.alpha:id/search_src_text"));
    private final ElementsCollection textElements = $$(className("android.widget.TextView"));

    @Step("Открыть поиск")
    public MainWikiScreen clickSearchIcon() {
        $(accessibilityId("Search Wikipedia")).click();
        return this;
    }

    @Step("Ввести в строку поиска запрос")
    public MainWikiScreen enterSearchQuery(String query) {
        searchInput.sendKeys(query);
        return this;

    }

    @Step("Проверить что контент найден")
    public void shouldSeeResults() {
        textElements.shouldHave(sizeGreaterThan(0));
    }

}
