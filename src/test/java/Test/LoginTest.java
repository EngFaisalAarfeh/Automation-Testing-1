package Test;

import Pages.*;
import Utilities.TestBase;
import Utilities.Utilities;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends TestBase {


       Login login = new Login(driver);
       Products products = new Products(driver);
       Cart cart = new Cart(driver);
       Checkout checkout = new Checkout(driver);

        Utilities utils = new Utilities();


    @BeforeMethod
    public void setUp() {
        login = new Login(driver);
        products = new Products(driver);
    }


    @Test
    public void validLoginTest() {
        utils.sleep(2000);

      //  login = new Login(driver); // bad code
        products = new Products(driver);
//        utils.highlightElement(login.loginButton, driver);
//
//        utils.sleep(5000);

        login.login("standard_user", "secret_sauce");




        Assert.assertEquals(products.getAppLogoText(), "Swag Labs","Logo is not right");
    }

    @Test
    public void invalidLoginTest() {

     //   login = new Login(driver); // bad code

        login.login("standard_user", "WrongPass_secret_sauce");
        Assert.assertEquals(login.getErrorMessage(), "Epic sadface: Username and password do not match any user in this service","Logo is not right");

    }


    @Test
    public void locked_out_userLoginTest() {
       // login = new Login(driver); // bad code
        login.login("locked_out_user", "secret_sauce");
        Assert.assertEquals(login.getErrorMessage(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Error Message In Not Correct");
    }

}
