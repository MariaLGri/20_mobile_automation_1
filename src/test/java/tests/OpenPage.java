package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;

public class OpenPage extends TestBase2  {
    @Test
    void shouldOpenArticlePage() {
        step("Выполнить поиск статьи", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text"))
                    .sendKeys("Appium");
        });

        step("Выбрать первую статью из результатов", () -> {
            $(id("org.wikipedia.alpha:id/page_list_item_title"))
//            android.widget.TextView
                    .shouldBe(visible)
                    .click();
        });

        step("Проверить открытие страницы статьи", () -> {
            // Проверяем наличие заголовка статьи
            $(id("org.wikipedia.alpha:id/view_page_title_text"))
                    .shouldBe(visible);

            // Проверяем наличие контента статьи
            $(id("org.wikipedia.alpha:id/view_page_subtitle_text"))
                    .shouldBe(visible);

            // Альтернативная проверка - наличие кнопки "Назад"
            $(accessibilityId("Navigate up"))
                    .shouldBe(visible);
        });

    }
}
