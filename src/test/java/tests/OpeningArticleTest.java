package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import screens.MainWikiScreen;
import screens.RequestedAppiumSeleniumScreen;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Wikipedia мобильные тесты")
@Owner("Гришина М.Л.")
public class OpeningArticleTest extends TestBase {

    MainWikiScreen mainPage = new MainWikiScreen();
    RequestedAppiumSeleniumScreen requestedAppiumSeleniumScreen = new RequestedAppiumSeleniumScreen();

    @ParameterizedTest(name = "Поиск по запросу \"{0}\" и открытие статьи")
    @Severity(SeverityLevel.CRITICAL)
    @CsvSource({
            "Appium, Appium",
            "Selenium, Selenium"
    })
    void openArticleTest(String searchQuery, String expectedTitle) {
            mainPage.clickSearchIcon()
                    .enterSearchQuery(searchQuery);
            requestedAppiumSeleniumScreen.openFirstSearchResult()
                    .shouldSeeArticleTitle(expectedTitle);
    }

    @Test
    @DisplayName("Проверка кнопки возврата 'Назад' и закрытия приложения")
    @Severity(SeverityLevel.CRITICAL)
    void returnBackTest() {
        mainPage.clickSearchIcon()
                .enterSearchQuery(searchQuery);
        requestedAppiumSeleniumScreen.openFirstSearchResult()
                .clickBackButton();
        step("Проверяем что приложение закрылось", () -> {
            assertThat(requestedAppiumSeleniumScreen.isAppClosed())
                    .as("Приложение должно было закрыться после нажатия кнопки 'Назад'")
                    .isTrue();
        });
    }
}