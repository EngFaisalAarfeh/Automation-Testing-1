package Test;

import Pages.Cart;
import Pages.Checkout;
import Pages.Login;
import Pages.Products;
import Utilities.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductTest extends TestBase {



    Login login = null;
    Products products = null;
    Cart cart = null;
    Checkout checkout = null;

    @BeforeMethod
    public void setUp() {
        login = new Login(driver);
        products = new Products(driver);
        cart = new Cart(driver);
        checkout= new Checkout(driver);
    }

    SoftAssert softAssert = new SoftAssert();
    @Test
    public void addnewProductToCart() {
        login.login_2("standard_user", "secret_sauce");
        softAssert.assertEquals(products.getAppLogoText(), "Swag Labs", "Logo is not right");
        products.addProductToCart("Sauce Labs Backpack");
        cart.checkout();
        checkout.CheckoutYourInformation("Faisal", "Arafeh", "56487");
        // softAssert.assertEquals(checkout.getTotalPrice(), "Total: $32.39", "Total Price is not right");
        softAssert.assertEquals(checkout.totalPrice.getText(), "Total: $32.39", "Total Price is not right");
        checkout.clickOnFinish();
        softAssert.assertEquals(checkout.getThankYouMessage(),
                "Thank you for your order!", "Thank you for your order! Message");
        softAssert.assertAll();
    }





}
