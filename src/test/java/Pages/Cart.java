package Pages;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends TestBase {


    public Cart(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "checkout")
    WebElement checkout;

    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement remove;


    @FindBy(id = "remove-sauce-labs-backpack")
    WebElement ContinueShopping;


    public void checkout() {
       // checkout.click();
        click(checkout);
    }

    public void remove() {
       // remove.click();
        click(remove);
    }

}
