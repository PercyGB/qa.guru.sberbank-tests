package cloud.autotests.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class WebSteps {

    private final static String URL = "https://www.sberbank.ru/ru/person";

    @Step("Открываем главную страницу {url}")
    public void openMainPage(){
        open(URL);
    }

    @Step("Переходим к блоку \"Калькулятор\"")
    public void navigateToCreditAndDepositCalc() {
            $("#creditAndDepositCalc").scrollIntoView(true);
    }

    @Step("Переходим на вкладку \"{calcTabName}\"")
    public void switchToCalcTab(String calcTabName){
            $(".static-calculator__buttons").$(byText(calcTabName)).click();
    }

    @Step("Устанавливаем сумму вклада, равную {depositValue}")
    public void setDepositAmount(String depositValue){
            $("[name='depositSum']").click();
            $("[name='depositSum']").setValue(depositValue);
            $("#creditAndDepositCalc").click();
        };

    @Step("Проверяем, что значение дохода по вкладу равно {depositIncome}")
    public void checkDepositIncome(String depositIncome){
            $("[data-test-id='AnimatedString_Value-undefined']").shouldHave(text(depositIncome));
    }

    @Step("Проверяем, что значение ставки по вкладу равно {depositRate}")
    public void checkDepositRate(String depositRate){
            $("[data-test-id='AnimatedString_Value-undefined']").shouldHave(text(depositRate));
    }

    @Step("Переходим по ссылке \"{linkName}\"")
    public void clickToLink(String linkName){
        $(By.linkText(linkName)).scrollTo().click();
    }

    @Step("Проверяем, что название страницы \"{headerText}\"")
    public void checkPageHeader(String headerText){
        $(byTagName("h1")).shouldHave(text(headerText));
    }

}
