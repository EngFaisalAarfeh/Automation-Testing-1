import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Basics {

//    public static void main(String[] args) {
//        System.out.println("Faisal");
//    }

//public static void main(String[] args) throws InterruptedException {
//    String driverPath = "src/main/resources/chromedriver.exe";
//
//    System.setProperty("webdriver.chrome.driver", driverPath);
//    WebDriver driver = new ChromeDriver(); // open browser
//    driver.get("https://www.google.com");// Go to google.com
//    System.out.println("Page Title: " + driver.getTitle());
//    Thread.sleep(5000);
//    driver.quit();
//}

//public static void main(String[] args) throws InterruptedException {
//    WebDriver driver = new ChromeDriver();
//
//    driver.get("https://www.facebook.com");
//    System.out.println("Page Title: " + driver.getTitle());
//    Thread.sleep(5000);
//    driver.quit();
//}


    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new FirefoxDriver();

        driver.get("https://www.facebook.com");
        System.out.println("Page Title: " + driver.getTitle());
        Thread.sleep(5000);
        driver.quit();
    }

    WebDriver driver = new ChromeDriver();

    @Test
    public void BrowserCommands() throws InterruptedException {
        driver.get("https://google.com");
        TimeUnit.SECONDS.sleep(3);

        driver.navigate().to("https://openai.com/");
        TimeUnit.SECONDS.sleep(3);

        driver.navigate().back();
        TimeUnit.SECONDS.sleep(3);

        driver.navigate().forward();
        TimeUnit.SECONDS.sleep(3);

        driver.navigate().refresh();
        TimeUnit.SECONDS.sleep(3);

        driver.quit();
    }

    @Test
    public void MaximizeWindow() throws InterruptedException {
        driver.get("https://www.linkedin.com/");
        Thread.sleep(3000);
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void BrowserOptions() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--start-maximized");
        //options.addArguments("--window-size=1920,1080");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--incognito");
        options.addArguments("--disable-extensions");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.linkedin.com/");
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void BrowserOptionsHeadless() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.facebook.com/");
        System.out.println("Page Title: " + driver.getTitle());
        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void loginTest() throws InterruptedException {
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(3000);// Bad Code

//        driver.findElement(By.id("username")).sendKeys("Faisal_Username");
//        Thread.sleep(2000);
//        driver.findElement(By.id("username")).clear();
//        Thread.sleep(2000);

        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("Faisal_Username"); // Bad Code
        driver.findElement(By.id("password")).sendKeys("Faisal_Password");
        driver.findElement(By.tagName("button")).click();

        String errorMessage = driver.findElement(By.xpath(
                "//div[contains(@class,'ng-binding ng-scope')]")).getText();

        Thread.sleep(3000);
        System.out.println("Error Message: " + errorMessage);
        Thread.sleep(3000);

        driver.quit();
    }


    @Test
    public void js() throws InterruptedException {
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(2000);

        WebElement username = driver.findElement(By.id("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("arguments[0].value = arguments[1];", username, "Faisal_UserName");
        js.executeScript("arguments[0].value = arguments[1];", pass, "MyPassword");

        js.executeScript("arguments[0].removeAttribute('disabled');", loginButton);
        js.executeScript("arguments[0].click();", loginButton);

        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void Action() throws InterruptedException {
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(2000);

        WebElement username = driver.findElement(By.id("username"));
        WebElement pass = driver.findElement(By.name("password"));
        WebElement loginButton = driver.findElement(By.tagName("button"));

        Actions actions = new Actions(driver);

        actions.sendKeys(username, "Faisal").perform();
        actions.sendKeys(pass, "MyPass").perform();
        actions.click(loginButton).perform();

        Thread.sleep(5000);


        driver.quit();
    }


    @Test
    public void RelativeLocator() throws InterruptedException {
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(2000);

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("User");

        WebElement passwordField =
                driver.findElement(with(By.tagName("input")).below(username));
        passwordField.sendKeys("Faisal_Password");

        //WebElement loginButton = driver.findElement(By.tagName("button"));

        Thread.sleep(5000);

        driver.quit();
    }

    @Test
    public void RelativeLocator_2() throws InterruptedException {
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(2000);

        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("myPassword");

        WebElement userName = driver.findElement(with(By.tagName("input")).above(password));
        userName.sendKeys("Above Password");




        Thread.sleep(5000);


        driver.quit(); // Faisal new update
    }










}





