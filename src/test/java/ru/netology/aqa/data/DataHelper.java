package ru.netology.aqa.data;

import lombok.Value;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;
import java.util.Calendar;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String code;


        public Object substring(int i, int i1) {
        }
    }
 
    public static CardInfo getApprovedCardInfo() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getDeclinedCardInfo() {
        return new CardInfo(getCardNumberDeclined(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    //    public static CardInfo getFifteenCharactersNumberCardInfo() {
//        return new CardInfo(getCardNumberFifteenCharacters(),
//                generateExpirationMonth(),
//                generateYear(),
//                generateName("en"),
//                generateSecurityCode()
//        );
//    }
    public static CardInfo getNotNumberCardInfo() {
        return new CardInfo(null,
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardWithNotMonth() {
        return new CardInfo(getCardNumberApproved(),
                getNotMonth(),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardWithNotYear() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                getNotYear(),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardWithNotName() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                generateYear(0),
                getNotName(),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardWithNotSecurityCode() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                getNotSecurityCode()
        );
    }

    public static CardInfo getDeclinedWithNotNumber() {
        return new CardInfo(getNotCardNumber(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getDeclinedWithNotMonth() {
        return new CardInfo(getCardNumberDeclined(),
                getNotMonth(),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getDeclinedWithNotYear() {
        return new CardInfo(getCardNumberDeclined(),
                generateMonth(2),
                getNotYear(),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getDeclinedWithNotName() {
        return new CardInfo(getCardNumberDeclined(),
                generateMonth(2),
                generateYear(0),
                getNotName(),
                generateSecurityCode()
        );
    }

    public static CardInfo getDeclinedWithNotSecurityCode() {
        return new CardInfo(getCardNumberDeclined(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                getNotSecurityCode()
        );
    }

    public static CardInfo getCardNumberOneCharacters() {
        return new CardInfo(getNumberOneCharacters(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getCardNumberFifteenCharacters() {
        return new CardInfo(getNumberFifteenCharacters(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getCardNumberSeventeenCharacters() {
        return new CardInfo(getNumberSeventeenCharacters(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getCardNumberWithLetter() {
        return new CardInfo(getNumberWithLetter(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }


    public static CardInfo getCardNumberWithSymbols() {
        return new CardInfo(getNumberWithSymbols(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardWithOneSymbolFromMonth() {
        return new CardInfo(getCardNumberApproved(),
                getMonthWithOneSymbol(),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardPastMonth() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(-2),
                generateYear(0),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardWithOneSymbolFromYear() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                getYearWithOneSymbol(),
                generateName("en"),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardPastYear() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                generateYear(-1),
                generateName("en"),
                generateSecurityCode()
        );
    }

        public static CardInfo getApprovedCardWithCyrillicName() {
            return new CardInfo(getCardNumberApproved(),
                    generateMonth(2),
                    generateYear(0),
                    getCyrillicName(),
                    generateSecurityCode()
            );
    }

    public static CardInfo getApprovedCardWithNameOneSymbol() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                generateYear(0),
                getNameWithOneSymbol(),
                generateSecurityCode()
        );
    }

    public static CardInfo getApprovedCardWithCodeOneSymbol() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                getCodeWithOneSymbol()
        );
    }

    public static CardInfo getApprovedCardWithCodeTwoSymbol() {
        return new CardInfo(getCardNumberApproved(),
                generateMonth(2),
                generateYear(0),
                generateName("en"),
                getCodeWithTwoSymbol()
        );
    }

    private static final Random random = new Random();
    private static final Faker faker = new Faker();

    public static String getCardNumberApproved() {
        return "1111222233334444";
    }

    public static String getCardNumberDeclined() {
        return "5555666677778888";
    }

    public static String getNotCardNumber() {
        return "";
    }

    public static String getNumberOneCharacters() {
        return faker.number().digits(1);
    }

    public static String getNumberFifteenCharacters() {
        return faker.number().digits(15);
    }

    public static String getNumberSeventeenCharacters() {
        return "11112222333344444";
    }

    public static String getNumberWithLetter() {
        return "A";
    }

    public static String getNumberWithSymbols() {
        return "№№№";
    }

    public static String generateMonth(int addMonths) {
        return LocalDate.now().plusMonths(addMonths).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getNotMonth() {
        return "";
    }

    public static String getMonthWithOneSymbol() {
        return faker.number().digits(1);
    }

    public static String generateYear(int addYears) {
        return LocalDate.now().plusYears(addYears).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getNotYear() {
        return "";
    }

    public static String getYearWithOneSymbol() {
        return faker.number().digits(1);
    }

    public static String generateName(String locale) {
        var faker = new Faker(new Locale(locale));
        String randomName = (faker.name().lastName() + " " + faker.name().firstName());
        return randomName.replace('ё', 'е');
    }

    public static String getNotName() {
        return "";
    }

    public static String getCyrillicName() {
        return "Маша Иванова";
    }

    public static String getNameWithOneSymbol() {
        return "V";
    }

    public static String generateSecurityCode() {
        int code = random.nextInt(1000);
        return String.format("%03d", code); // Formats the code as a 3-digit number with leading zeros if necessary
    }

    public static String getNotSecurityCode() {
        return "";
    }
    public static String getCodeWithOneSymbol() {
        return faker.number().digits(1);
    }
    public static String getCodeWithTwoSymbol() {
        return faker.number().digits(2);
    }

}
