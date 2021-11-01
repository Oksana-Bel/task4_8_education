import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchPageSoftAssertions {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void findSoftAssertionsInGitHub() {
        String s;
        //Откройте страниц Selenide в Github
        open("https://github.com");
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        $(".repo-list").$("li", 0).$("a").click();
        $("h1").should(text("selenide / selenide"));
        //Перейдите в раздел Wiki проекта
        $(byText("Wiki")).click();
        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $(".js-wiki-more-pages-link").scrollIntoView(false).click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));
        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
        $("#wiki-pages-box").$(byText("SoftAssertions")).scrollIntoView(false).click();
        $(".markdown-body").shouldHave(text("Using JUnit5 extend test class"));

    }
}

