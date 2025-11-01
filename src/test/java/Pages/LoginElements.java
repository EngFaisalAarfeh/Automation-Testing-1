package Pages;

import Utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginElements extends TestBase {

    public LoginElements(WebDriver driver) { // Step-1

        this.driver = driver;
    }

    // Step-2
    private By userName = By.id("user-name");
    private By password = By.id("password");
    public By loginButton = By.id("login-button");
    public By errorMessage = By.tagName("h3");
    public By logo = By.className("app_logo");

    public String getLogoText() {


   //     waitElementToBeVisible(logo);
        return driver.findElement(logo).getText();
    }

    // Step-3
    private void enterUserName(String userName) {
        driver.findElement(this.userName).sendKeys(userName);
    }
    private void enterPassword(String passwordvalue) {
        driver.findElement(password).sendKeys(passwordvalue); // bad code
    }

    private void TapLoginButton() {
        driver.findElement(loginButton).click(); // bad code
    }

    // Step-4
    public void login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        TapLoginButton();
    }

    public void login_2(String userName, String password){
        driver.findElement(this.userName).sendKeys(userName);
        driver.findElement(this.password).sendKeys(password); // bad code
        driver.findElement(loginButton).click(); // bad code
    }




}
