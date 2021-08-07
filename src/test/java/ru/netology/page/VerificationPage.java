package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.page;

public class VerificationPage {
    @FindBy(css = "[data-test-id=code] input")
    private SelenideElement verifyField;
    @FindBy(css = "[data-test-id=action-verify]")
    private SelenideElement verifyButton;

    public CardsPage validationTrue(DataHelper.VerificationCode code) {
        verifyField.setValue(code.getCode());
        verifyButton.click();
        return page(CardsPage.class);
    }
}
