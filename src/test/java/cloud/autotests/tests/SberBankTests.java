package cloud.autotests.tests;

import cloud.autotests.annotations.Layer;
import cloud.autotests.annotations.Microservice;
import cloud.autotests.helpers.DriverUtils;
import cloud.autotests.steps.WebSteps;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import cloud.autotests.annotations.JiraIssue;
import cloud.autotests.annotations.JiraIssues;

import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.*;


@Layer("web")
public class SberBankTests extends TestBase {

    WebSteps steps = new WebSteps();

    @Test
    @Tag("Smoke")
    @DisplayName("Проверка заголовка страницы")
    @JiraIssues({@JiraIssue("HOM-236")})
    @Owner("ivanovi")
    void titleTest() {
        steps.openMainPage();

        step("Проверяем, что заголовок страницы содеожит 'Частным клиентам — СберБанк'", () -> {
            String expectedTitle = "Частным клиентам — СберБанк";
            String actualTitle = title();

            assertThat(actualTitle).isEqualTo(expectedTitle);
        });
    }

    @Test
    @Tag("Smoke")
    @DisplayName("Проверка консоли браузера на наличие ошибок")
    @JiraIssues({@JiraIssue("HOM-236")})
    @Owner("ivanovi")
    void consoleShouldNotHaveErrorsTest() {
        steps.openMainPage();

        step("Проверяем, что консоль браузера не содержит ошибок", () -> {
            String consoleLogs = DriverUtils.getConsoleLogs();
            String errorText = "SEVERE";

            assertThat(consoleLogs).doesNotContain(errorText);
        });
    }

    @Test
    @Feature("Калькулятор вкладов")
    @DisplayName("Проверка мининимального значения величины вклада")
    @JiraIssues({@JiraIssue("HOM-236")})
    @Microservice("Deposit")
    @Owner("kuznetsoval")
    void minValueOfDepositAmountTest(){
        steps.openMainPage();
        steps.navigateToCreditAndDepositCalc();
        steps.switchToCalcTab("Вклад");
        steps.setDepositAmount("999");

        step("Проверяем, что сумма вклада равна 1000", () -> {
            $("[name='depositSum']").shouldHave(value("1 000"));
        });
    }


    @DisplayName("Проверка величины дохода по вкладу")
    @Feature("Калькулятор вкладов")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + ": " + ARGUMENTS_WITH_NAMES_PLACEHOLDER)
    @CsvSource({"100000, 3 401,91", "300000, 10 205,74", "500000, 17 783,46"})
    @JiraIssues({@JiraIssue("HOM-236")})
    @Microservice("Deposit")
    @Owner("kuznetsoval")
    void depositIncomeValueTest(String depositAmount, String depositIncome) {
        steps.openMainPage();
        steps.navigateToCreditAndDepositCalc();
        steps.switchToCalcTab("Вклад");
        steps.setDepositAmount(depositAmount);
        steps.checkDepositIncome(depositIncome);
    }

    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + ": " + ARGUMENTS_WITH_NAMES_PLACEHOLDER)
    @CsvSource({"100000, 3,4", "500000, 3,56", "1000000, 3,56"})
    @DisplayName("Проверка величины процента по вкладу")
    @Feature("Калькулятор вкладов")
    @JiraIssues({@JiraIssue("HOM-236")})
    @Microservice("Deposit")
    @Owner("kuznetsoval")
    void depositRateValueTests(String depositAmount, String depositRate) {
        steps.openMainPage();
        steps.navigateToCreditAndDepositCalc();
        steps.switchToCalcTab("Вклад");
        steps.setDepositAmount(depositAmount);
        steps.checkDepositRate(depositRate);
    }

    @Test
    @DisplayName("Проверка работы ссылки \"Все вопросы и ответы\"")
    @Feature("Навигация по ссылкам")
    @JiraIssues({@JiraIssue("HOM-236")})
    @Owner("ivanovi")
    void qAndALinkTest(){
        steps.openMainPage();
        steps.clickToLink("Все вопросы и ответы");
        steps.checkPageHeader("Вопросы и ответы");
    }

    @Test
    @DisplayName("Проверка работы ссылки \"Все новости\"")
    @Feature("Навигация по ссылкам")
    @JiraIssues({@JiraIssue("HOM-236")})
    @Owner("ivanovi")
    void allNewsLinkTest(){
        steps.openMainPage();
        steps.clickToLink("Все новости");
        steps.checkPageHeader("Пресс-релизы Банка");
    }
}