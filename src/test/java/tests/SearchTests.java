package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.SearchMainPage;
import pages.SearchPage1;


import static io.qameta.allure.Allure.step;


@DisplayName("Wikipedia мобильные тесты")
@Owner("Гришина М.Л.")
public class SearchTests extends TestBase {

    SearchMainPage searchMainPage = new SearchMainPage();
    SearchPage1 searchPage1 = new SearchPage1();

    @Test
    @DisplayName("Проверка успешного поиска статей")
    @Severity(SeverityLevel.CRITICAL)
    void successfulSearchTest() {
        step("Откройте поиск и введите запрос", () -> {
            searchMainPage.clickSearchIcon();
            searchPage1.enterSearchQuery("Appium");
        });

        step("Проверить что контент найден", searchPage1::shouldSeeResults);
    }
}