package tests;

import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;
import static io.qameta.allure.Allure.step;



public class SearchTests extends TestBase{


    @Test
    void successfulSearchTest() {
        step("Выполнить поиск статьи", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Проверить результат поиска (> 0 )", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }


}
