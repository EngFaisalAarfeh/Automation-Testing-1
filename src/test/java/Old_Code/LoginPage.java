package Old_Code;

import Utilities.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends TestBase {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(tagName = "h3")
    public WebElement errorMessage;

    public String errorMessage() {
        return errorMessage.getText();
    }

    public void login(String userName, String password) {
        sendKey(username, userName);
        sendKey(this.password, password);
        click(loginButton);

    }
}
