package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.SearchMainPage;
import pages.SearchPage1;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Wikipedia мобильные тесты")
@Owner("Гришина М.Л.")
public class OpeningArticleTest extends TestBase {

    SearchMainPage mainPage = new SearchMainPage();
    SearchPage1 searchPage1 = new SearchPage1();

    @ParameterizedTest(name = "Поиск по запросу \"{0}\" и открытие статьи")
    @Severity(SeverityLevel.CRITICAL)
    @CsvSource({
            "Appium, Appium",
            "Selenium, Selenium"
    })
    void openArticleTest(String searchQuery, String expectedTitle) {
        step("Выполняем поиск", () -> {
            mainPage.clickSearchIcon();
            searchPage1.enterSearchQuery(searchQuery);
        });

        step("Открываем первую статью", searchPage1::openFirstSearchResult);

        step("Проверяем заголовок статьи", () -> searchPage1.shouldSeeArticleTitle(expectedTitle));
    }

    @Test
    @DisplayName("Проверка кнопки возврата 'Назад' и закрытия приложения")
    @Severity(SeverityLevel.CRITICAL)
    void returnBackTest() {
        String searchQuery = "Appium";


        step("Открыть поиск и ввести запрос", () -> {
            mainPage.clickSearchIcon();
            searchPage1.enterSearchQuery(searchQuery);
        });

        step("Открываем первую статью", searchPage1::openFirstSearchResult);

        step("Нажать кнопку назад", () -> searchPage1.clickBackButton());

        step("Проверяем что приложение закрылось", () -> {
            assertThat(searchPage1.isAppClosed())
                    .as("Приложение должно было закрыться после нажатия кнопки 'Назад'")
                    .isTrue();
        });
    }
}