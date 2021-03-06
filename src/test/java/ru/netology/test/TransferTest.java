package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferTest {
    @BeforeEach
    public void setUpAll() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferToFirstFromSecond() {
        var login = open("http://localhost:9999", LoginPage.class);
        var auth = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(auth);
        var verify = login.validLogin(auth);
        var cards = verify.validationTrue(code);
        var cardsInfo = DataHelper.getCardsInfo();
        int fistBalance = cards.getFistBalance();
        int secondBalance = cards.getSecondBalance();
        int transferSum = 1000;
        var transferToFirst = cards.transferToFirst();
        transferToFirst.transfer(Integer.toString(transferSum), cardsInfo, 1);
        assertEquals(fistBalance + transferSum, cards.getFistBalance());
        assertEquals(secondBalance - transferSum, cards.getSecondBalance());
    }

    @Test
    void shouldTransferToSecondFromFirst() {
        var login = open("http://localhost:9999", LoginPage.class);
        var auth = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(auth);
        var verify = login.validLogin(auth);
        var cards = verify.validationTrue(code);
        var cardsInfo = DataHelper.getCardsInfo();
        int fistBalance = cards.getFistBalance();
        int secondBalance = cards.getSecondBalance();
        int difference = 500;
        var transferToSecond = cards.transferToSecond();
        transferToSecond.transfer(Integer.toString(difference), cardsInfo, 2);
        assertEquals(fistBalance - difference, cards.getFistBalance());
        assertEquals(secondBalance + difference, cards.getSecondBalance());
    }

    @Test
    @DisplayName("Sending over limit")
    void shouldNotTransferMoneyOverLimit() {
        var login = open("http://localhost:9999", LoginPage.class);
        var auth = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(auth);
        var verify = login.validLogin(auth);
        var cards = verify.validationTrue(code);
        var cardsInfo = DataHelper.getCardsInfo();
        int fistBalance = cards.getFistBalance();
        int secondBalance = cards.getSecondBalance();
        int difference = 20000;
        var transferToFirst = cards.transferToFirst();
        transferToFirst.transfer(Integer.toString(difference),cardsInfo, 1);
        assertEquals(fistBalance, cards.getFistBalance());
        assertEquals(secondBalance, cards.getSecondBalance());
    }
}
