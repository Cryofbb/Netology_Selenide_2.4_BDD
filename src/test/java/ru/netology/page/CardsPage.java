package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$$;

public class CardsPage {
    @FindBy(css = "li:nth-child(1) .button")
    private SelenideElement firstCard;
    @FindBy(css = "li:nth-child(2) .button")
    private SelenideElement secondCard;
    @FindBy(css = "[data-test-id=action-reload]")
    private SelenideElement reloadButton;

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public CardsPage() {
    }

    public TransferPage transferToFirst() {
        firstCard.click();
        return new TransferPage();
    }

    public TransferPage transferToSecond() {
        secondCard.click();
        return new TransferPage();
    }

    public int getFistBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    public int getSecondBalance() {
        val text = cards.last().text();
        return extractBalance(text);
    }

    public int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}