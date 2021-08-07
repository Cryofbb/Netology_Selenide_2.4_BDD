package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.CardsPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TransferTest {
    @BeforeEach
    public void setUpAll() {
        open("http://localhost:9999");
        var login = new LoginPage();
        var auth = DataHelper.getAuthInfo();
        var code = DataHelper.getVerificationCodeFor(auth);
        var verify = login.validLogin(auth);
        verify.validationTrue(code);

    }

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        var cardsInfo = DataHelper.getCardsInfo();
        var cards = new CardsPage();
        int fistBalance = cards.getFistBalance();
        int secondBalance = cards.getSecondBalance();
        int difference = 200;
        var replenishThis = cards.transferToFirst();
        replenishThis.transfer(Integer.toString(difference), cardsInfo, 1);
        assertEquals(fistBalance + difference, cards.getFistBalance());
        assertEquals(secondBalance - difference, cards.getSecondBalance());
    }
}
