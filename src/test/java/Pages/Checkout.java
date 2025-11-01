package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Checkout extends TestBase {


    public Checkout(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstName;


    @FindBy(id = "last-name")
    WebElement lastName;


    @FindBy(id = "postal-code")
    WebElement postalCode;

    @FindBy(id = "continue")
    WebElement continueButton;


    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(className = "summary_total_label")
   public WebElement totalPrice;

    @FindBy(id = "finish")
   public WebElement finish;

    @FindBy(tagName = "h2")
   public WebElement thankYouMessage;

    public String getThankYouMessage() {
        return thankYouMessage.getText();
    }


    public void clickOnFinish() {
        finish.click();
    }


    public void CheckoutYourInformation(String firstNameValue, String lastNameValue, String postalCodeValue) {
        //firstName.sendKeys(firstNameValue);
        sendKey(firstName, firstNameValue);
      //  lastName.sendKeys(lastNameValue);
        sendKey(lastName, lastNameValue);
      //  postalCode.sendKeys(postalCodeValue);
        sendKey(postalCode, postalCodeValue);
       // continueButton.click();
        click(continueButton);

    }

    public String getTotalPrice() {
        return totalPrice.getText();
    }



}
