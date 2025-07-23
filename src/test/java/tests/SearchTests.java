package tests;

import org.junit.jupiter.api.Test;
import pages.SearchMainPage;
import pages.SearchPage1;


import static io.qameta.allure.Allure.step;



public class SearchTests extends TestBase {

    SearchMainPage searchMainPage = new SearchMainPage();
    SearchPage1 searchPage1 = new SearchPage1();

    @Test
    void successfulSearchTest() {
        step("Откройте поиск и введите запрос", () -> {
            searchMainPage.clickSearchIcon();
            searchPage1.enterSearchQuery("Appium");
        });

        step("Проверить что контент найден", searchPage1::shouldSeeResults);
    }
}