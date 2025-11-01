package Utilities;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public class TestBase {

   protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setup(@Optional("chrome") String browser) {

        if(browser.equalsIgnoreCase("chrome")){

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);

        }

        else if(browser.equalsIgnoreCase("chrome-headless"))
        {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("--start-maximized");
            options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        }
        else if(browser.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }




        driver.get("https://www.saucedemo.com/");
    }

    @AfterMethod(alwaysRun = true)
    public void teardown() {

        if (driver != null) {

            driver.quit();
        }
    }


 public void click(WebElement element){
        waitElementToBeVisible(element);
        element.click();
 }

    public void click(WebElement element, String Message){
        waitElementToBeVisible(element);
        element.click();

        if (ExtentManager.getTest()!=null) {
            ExtentManager.getTest().log(Status.INFO, "Clicked On: "+Message);}
    }


public void sendKey(WebElement element, String text){
    waitElementToBeVisible(element);

    element.sendKeys(text);

}

    protected void waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }








}
