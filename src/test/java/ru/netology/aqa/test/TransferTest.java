package ru.netology.aqa.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.aqa.data.DataHelper;
import ru.netology.aqa.page.DashboardPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TransferTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    DashboardPage dashboardPage = new DashboardPage();


    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED")
    void shouldSuccessfulPaymentWithApprovedCard() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardInfo();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageSuccess();
        Assertions.assertEquals("APPROVED", ru.netology.data.SQLHelper.findPayStatus());
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте DECLINED")
    void shouldRejectionPaymentWithDeclinedCard() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var DeclinedCard = DataHelper.getDeclinedCardInfo();
        dashboardPage.makeTransfer(DeclinedCard);
        dashboardPage.messageError();
        Assertions.assertEquals("DECLINED", ru.netology.data.SQLHelper.findPayStatus());
    }

    @Test
    @DisplayName("Оплата с помощью кредитных средств по дебетовой карте APPROVED")
    void shouldSuccessfulCreditWithApprovedCard() {
        open("http://localhost:8080");
        dashboardPage.creditGate();
        var ApprovedCard = DataHelper.getApprovedCardInfo();
        dashboardPage.makeTransfer(ApprovedCard);
        dashboardPage.messageSuccess();
        Assertions.assertEquals("APPROVED", ru.netology.data.SQLHelper.findCreditStatus());
    }

    @Test /* Issues #1 */
    @DisplayName("Оплата с помощью кредитных средств по дебетовой карте DECLINED")
    void shouldErrorCreditWithDeclinedCard() {
        open("http://localhost:8080");
        dashboardPage.creditGate();
        var declinedCard = DataHelper.getDeclinedCardInfo();
        dashboardPage.makeTransfer(declinedCard);
        dashboardPage.messageError();
        Assertions.assertEquals("DECLINED", ru.netology.data.SQLHelper.findCreditStatus());
    }

    @Test /* Issues #2 */
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с незаполненным полем \"Номер карты\"")
    void shouldErrorPaymentApprovedCardWithEmptyNumber() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getNotNumberCardInfo();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageFromRequired();
    }

    @Test /* Issues #3 */
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с незаполненным полем  \"Месяц\"")
    void shouldErrorPaymentApprovedCardWithEmptyMonth() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithNotMonth();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageFromRequired();
    }

    @Test /* Issues #4 */
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с незаполненным полем  \"Год\"")
    void shouldErrorPaymentApprovedCardWithEmptyYear() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithNotYear();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageFromRequired();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с незаполненным полем  \"Владелец\"")
    void shouldErrorPaymentApprovedCardWithEmptyName() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithNotName();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageFromRequired();
    }

    @Test /* Issues #5 */
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с незаполненным полем  \"CVC/CVV\"")
    void shouldErrorPaymentApprovedCardWithEmptyCode() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithNotSecurityCode();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageFromRequiredCode();
    }


    @Test /* Issues #6 */
    @DisplayName(" Оплата с помощью кредитных средств по дебетовой карте DECLINED с незаполненным полем \"Номер карты\"")
    void shouldErrorCreditDeclinedCardWithEmptyNumber() {
        open("http://localhost:8080");
        dashboardPage.creditGate();
        var card = DataHelper.getNotNumberCardInfo();
        dashboardPage.makeTransfer(card);
        dashboardPage.messageFromRequired();
    }

    @Test /* Issues #7 */
    @DisplayName("Оплата с помощью кредитных средств по дебетовой карте DECLINED с незаполненным полем  \"Месяц\"")
    void shouldErrorCreditDeclinedCardWithEmptyMonth() {
        open("http://localhost:8080");
        dashboardPage.creditGate();
        var card = DataHelper.getDeclinedWithNotMonth();
        dashboardPage.makeTransfer(card);
        dashboardPage.messageFromRequired();
    }

    @Test /* Issues #8 */
    @DisplayName("Оплата с помощью кредитных средств по дебетовой карте DECLINED с незаполненным полем  \"Год\"")
    void shouldErrorCreditDeclinedCardWithEmptyYear() {
        open("http://localhost:8080");
        dashboardPage.creditGate();
        var card = DataHelper.getDeclinedWithNotYear();
        dashboardPage.makeTransfer(card);
        dashboardPage.messageFromRequired();
    }

    @Test
    @DisplayName("Оплата с помощью кредитных средств по дебетовой карте DECLINED с незаполненным полем  \"Владелец\"")
    void shouldErrorCreditDeclinedCardWithEmptyName() {
        open("http://localhost:8080");
        dashboardPage.creditGate();
        var card = DataHelper.getDeclinedWithNotName();
        dashboardPage.makeTransfer(card);
        dashboardPage.messageFromRequired();
    }

    @Test /* Issues #9 */
    @DisplayName("Оплата с помощью кредитных средств по дебетовой карте DECLINED с незаполненным полем  \"CVC/CVV\"")
    void shouldErrorCreditDeclinedCardWithEmptyCode() {
        open("http://localhost:8080");
        dashboardPage.creditGate();
        var card = DataHelper.getDeclinedWithNotSecurityCode();
        dashboardPage.makeTransfer(card);
        dashboardPage.messageFromRequiredCode();
    }

    @Test /* Issues #10 */
    @DisplayName("Обычная оплата по дебетовой c невалидным вводом номера карты (из одной цифры)")
    void shouldErrorPaymentCardWithNumberOneCharacters() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getCardNumberOneCharacters();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalidCardNumber();
    }

    @Test /* Issues #11 */
    @DisplayName("Обычная оплата по дебетовой c невалидным вводом номера карты (15 цифр)")
    void shouldErrorPaymentCardWithNumberFifteenCharacters() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getCardNumberFifteenCharacters();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalidCardNumber();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой c невалидным вводом номера карты (17 цифр)")
    void shouldErrorPaymentCardWithNumberSeventeenCharacters() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getCardNumberSeventeenCharacters();
        var firstSixteenDigits = approvedCard.substring(0, 16);
        Assertions.assertEquals("1111222233334444", firstSixteenDigits);
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте c невалидным вводом номера карты (ввод букв)")
    void shouldErrorPaymentCardWithLetterInNumber() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var card = DataHelper.getCardNumberWithLetter();
        dashboardPage.makeTransfer(card);
        dashboardPage.messageAboutInvalidCardNumber();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте c невалидным вводом номера карты (ввод символов)")
    void shouldErrorPaymentCardWithSymbolInNumber() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var card = DataHelper.getCardNumberWithSymbols();
        dashboardPage.makeTransfer(card);
        dashboardPage.messageAboutInvalidCardNumber();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом одной цифры в поле \"Месяц\"")
    void shouldErrorPaymentApprovedCardWithOneSymbolFromMonth() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithOneSymbolFromMonth();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом с вводом" +
            " в поле \"Месяц\" прошедшего месяца текущего года.")
    void shouldErrorPaymentApprovedCardWithPastMonth() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardPastMonth();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutExpired();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом 3-ех цифр в поле \"Месяц\"")
    void shouldErrorPaymentApprovedCardWithThreeSymbolFromMonth() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardInMonthThreeSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом букв в поле \"Месяц\"")
    void shouldErrorPaymentApprovedCardWithLetterFromMonth() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardInMonthWithLetter();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом символов в поле \"Месяц\"")
    void shouldErrorPaymentApprovedCardWithSymbolFromMonth() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardInMonthWithSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом одной цифры в поле \"Год\"")
    void shouldErrorPaymentApprovedCardWithOneSymbolFromYear() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithOneSymbolFromYear();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом с вводом" +
            " в поле \"Год\" прошедшего года.")
    void shouldErrorPaymentApprovedCardWithPastYear() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardPastYear();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutExpired();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом 3-ех цифр в поле \"Год\"")
    void shouldErrorPaymentApprovedCardWithThreeSymbolFromYear() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardInYearThreeSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом букв в поле \"Год\"")
    void shouldErrorPaymentApprovedCardWithLetterFromYear() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardInYearWithLetter();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом символов в поле \"Год\"")
    void shouldErrorPaymentApprovedCardWithSymbolFromYear() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardInYearWithSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageAboutInvalid();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с невалидным вводом значения в поле \"Владелец\" (ввод кириллицы)")
    void shouldErrorPaymentApprovedCardWithWithCyrillicName() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithCyrillicName();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageErrorName();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с невалидным вводом значения в поле \"Владелец\" (ввод однй буквы)")
    void shouldErrorPaymentApprovedCardNameWithOneSymbol() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithNameOneSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageErrorName();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с невалидным вводом значения в поле \"Владелец\" (ввод символов)")
    void shouldErrorPaymentApprovedCardNameWithSymbol() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithNameSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.messageErrorName();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом одной цифры в поле \"СVC/CVV\"")
    void shouldErrorPaymentApprovedCardWithOneSymbolInCode() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithCodeOneSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.setMessageAboutInvalidCode();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом двух цифры в поле \"СVC/CVV\"")
    void shouldErrorPaymentApprovedCardWithTwoSymbolInCode() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithCodeTwoSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.setMessageAboutInvalidCode();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом 4-ех цифр в поле \"СVC/CVV\"")
    void shouldErrorPaymentApprovedCardWithFourSymbolInCode() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithCodeFourSymbol();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.setMessageAboutInvalidCode();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом букв в поле \"СVC/CVV\"")
    void shouldErrorPaymentApprovedCardWithLetterInCode() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithLetterInCode();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.setMessageAboutInvalidCode();
    }

    @Test
    @DisplayName("Обычная оплата по дебетовой карте APPROVED с вводом символов  в поле \"СVC/CVV\"")
    void shouldErrorPaymentApprovedCardWithSymbolInCode() {
        open("http://localhost:8080");
        dashboardPage.paymentGate();
        var approvedCard = DataHelper.getApprovedCardWithSymbolInCode();
        dashboardPage.makeTransfer(approvedCard);
        dashboardPage.setMessageAboutInvalidCode();
    }
}

