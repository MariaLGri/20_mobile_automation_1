package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import screens.MainWikiScreen;

@DisplayName("Wikipedia мобильные тесты")
@Owner("Гришина М.Л.")
public class SearchTests extends TestBase {

    MainWikiScreen mainWikiScreen = new MainWikiScreen();

    @Test
    @DisplayName("Проверка успешного поиска статей")
    @Severity(SeverityLevel.CRITICAL)
    void successfulSearchTest() {
        mainWikiScreen.clickSearchIcon()
                .enterSearchQuery("Appium")
                .shouldSeeResults();
    }
}