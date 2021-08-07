package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.data.DataHelper;

public class TransferPage {
    @FindBy(css = "[data-test-id=amount] input")
    private SelenideElement amount;
    @FindBy(css = "[data-test-id=from] input")
    private SelenideElement fromCard;
    @FindBy(css = "[data-test-id=to] input")
    private SelenideElement toCard;
    @FindBy(css = "[data-test-id=action-transfer]")
    private SelenideElement transferButton;
    @FindBy(css = "[data-test-id=action-cancel]")
    private SelenideElement cancelButton;

    public CardsPage transfer(String sum, DataHelper.CardsInfo cardsInfo, int number) {
        amount.setValue(sum);
        if (number == 1) {
            fromCard.setValue(cardsInfo.getFirst());
        }
        if (number == 2) {
            fromCard.setValue(cardsInfo.getSecond());
        }
        transferButton.click();
        return new CardsPage();
    }
}
