package ru.netology.aqa.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.aqa.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement buyButton = $$(".button__text").find(exactText("Купить"));
    private SelenideElement creditButton = $(byText("Купить в кредит"));

    private SelenideElement payment = $$(".heading").find(exactText("Оплата по карте"));
    private SelenideElement credit = $$(".heading")
            .find(exactText("Кредит по данным карты"));
    private SelenideElement numberCard = $$(".input__inner").findBy(text("Номер карты")).$(".input__control");
    private SelenideElement month = $$(".input__inner").findBy(text("Месяц")).$(".input__control");
    private SelenideElement year = $$(".input__inner").findBy(text("Год")).$(".input__control");
    private SelenideElement owner = $$(".input__inner").findBy(text("Владелец")).$(".input__control");
    private SelenideElement code = $$(".input__inner").findBy(text("CVC/CVV")).$(".input__control");
    private SelenideElement button = $(byText("Продолжить"));
    private SelenideElement messageSuccess = $$(".notification__title").find(exactText("Успешно"));
    private SelenideElement messageError = $(withText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement messageFromRequired = $$(".input__inner span.input__sub").find(exactText("Поле обязательно для заполнения"));
    //Для пустого поя СVC/CVV
    private SelenideElement messageFromRequiredCode =
            $$("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub").find(exactText("Поле обязательно для заполнения"));
    //для невалиного номера карты
    private SelenideElement messageAboutInvalidCardNumber = $$(".input__inner span.input__sub")
            .find(exactText("Неверно введен номер карты"));
    private SelenideElement messageAboutInvalid = $$(".input__inner span.input__sub")
            .find(exactText("Неверный формат"));
    private SelenideElement messageAboutExpired = $$(".input__inner span.input__sub")
            .find(exactText("Истёк срок действия карты"));
    private SelenideElement messageErrorName = $$(".input__inner span.input__sub")
            .find(exactText("Неверно указано имя, ведите имя латинскими буквами"));

    private SelenideElement messageAboutInvalidCode =
            $$("#root > div > form > fieldset > div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub")
                    .find(exactText("Неверно указан CVC/CVV"));
    public void paymentGate() {

        buyButton.click();
        payment.shouldBe(visible);
    }

    public void creditGate() {
        creditButton.click();
        credit.shouldBe(visible);
    }

    public void makeTransfer(DataHelper.CardInfo cardInfo) {
        numberCard.setValue(cardInfo.getCardNumber());
        month.setValue(cardInfo.getMonth());
        year.setValue(cardInfo.getYear());
        owner.setValue(cardInfo.getOwner());
        code.setValue(cardInfo.getCode());
        button.click();
    }

    public void messageSuccess() {
        messageSuccess.shouldBe(visible, Duration.ofSeconds(20));
    }

    public void messageError() {
        messageError.shouldBe(visible, Duration.ofSeconds(20));
    }

    public void messageFromRequired() {
        messageFromRequired.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void messageFromRequiredCode() {
        messageFromRequiredCode.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void messageAboutInvalidCardNumber() {
        messageAboutInvalidCardNumber.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void messageAboutInvalid() {
        messageAboutInvalid.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void messageAboutExpired() {
        messageAboutExpired.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void messageErrorName() {
        messageErrorName.shouldBe(visible, Duration.ofSeconds(15));
    }

    public void setMessageAboutInvalidCode() {
        messageErrorName.shouldBe(visible, Duration.ofSeconds(15));
    }
}
