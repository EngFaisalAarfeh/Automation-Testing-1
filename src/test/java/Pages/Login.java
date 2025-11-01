package Pages;

import Utilities.ExtentManager;
import Utilities.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login extends TestBase {


    public Login(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement userName;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
  public WebElement loginButton;


    @FindBy(tagName = "h3")
   public WebElement errorMessage;


   public String getErrorMessage(){

        return errorMessage.getText();
    }


    private void enterUserName1(String userNameValue){
       // userName.sendKeys(userNameValue); \\ bad code
        sendKey(userName, userNameValue);
    }

    private void enterUserName(String userNamevalue){
        sendKey(userName,userNamevalue);
       // userName.sendKeys(userNamevalue);
    }

    private void enterPassword(String passwordValue){
        sendKey(password,passwordValue);
       // password.sendKeys(passwordValue);
    }

    private void tapLoginButton(){
        click(loginButton,"Login Button");
    }


    public void login(String userName, String password ){
    enterUserName(userName);
    enterPassword(password);
    tapLoginButton();

        if (ExtentManager.getTest()!=null) {
            ExtentManager.getTest().log(Status.INFO,
                    "Login Screen Data: Username: "+ userName+" UserPassword: "+password);

            ExtentManager.getTest().log(Status.INFO, "Login Screen");
            ExtentManager.getTest().log(Status.INFO, "Used Username: "+userName);
            ExtentManager.getTest().log(Status.INFO, "Used Password: "+password);
       }

    }

    public void login_2(String userNamevalue, String passwordValue) {


        sendKey(userName, userNamevalue);
        sendKey(password, passwordValue);

        click(loginButton); // good code
        // loginButton.click(); // bad code
    }


}
