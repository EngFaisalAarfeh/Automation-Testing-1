package Old_Code;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNG_3 {

    WebDriver driver;


    // @BeforeMethod(groups = {"login","Cart"})
    @Parameters("browser")
    @BeforeMethod()
    public void setup(@Optional("chrome") String browser) throws InterruptedException {

        if(browser.equalsIgnoreCase("chrome")){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);

        } else if(browser.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }

        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);
    }

    @AfterMethod()
    public void teardown() {
        if (driver != null) {

            driver.quit();
        }
    }

    @Test(priority = 1, groups = {"login"})
    public void  LoginandCheckLogo_vaild_Login () throws InterruptedException {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        String logo = driver.findElement(By.className("app_logo")).getText();

        Assert.assertEquals(logo, "Swag Labs", "Logo is not right");
        Thread.sleep(3000);
    }


    @Test(priority = 2, groups = {"login"})
    public void  invalidLogin () throws InterruptedException {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secredsasasast_saasasuce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

       String errorMessage= driver.findElement(By.tagName("h3")).getText();

       Assert.assertEquals(errorMessage,
               "Epic sadface: Username and password do not match any user in this service", "Error Message is not right");

        Thread.sleep(3000);
    }



    @Test(priority = 2, groups = {"login"})
    public void  locked_out_user () throws InterruptedException {

        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        String errorMessage= driver.findElement(By.tagName("h3")).getText();

        Assert.assertEquals(errorMessage,
                "Epic sadface: Sorry, this user has been locked out.", "Error Message is not right");

        Thread.sleep(3000);
    }



    @Test(priority = 1, groups = {"Cart"})
    public void  LoginThenCheckCart () throws InterruptedException {

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();

        Thread.sleep(3000);
    }

//
//    @Test(priority = 1, groups = {"Cart"})
//    public void  LoginThenCheckCart_2 () throws InterruptedException {
//
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();
//        Thread.sleep(3000);
//
//        driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
//
//        Thread.sleep(3000);
//    }
//
//
//    @Test(priority = 1, groups = {"CheckOut"})
//    public void  CheckOut () throws InterruptedException {
//
//        driver.findElement(By.id("user-name")).sendKeys("standard_user");
//        driver.findElement(By.id("password")).sendKeys("secret_sauce");
//        driver.findElement(By.id("login-button")).click();
//        Thread.sleep(3000);
//
//        driver.findElement(By.xpath("//a[@data-test='shopping-cart-link']")).click();
//
//        Thread.sleep(3000);
//    }
//
//
//
//
//
//    @Test
//    public void startServer() {
//        System.out.println("Server started");
//    }
//
//
//    @Test(dependsOnMethods = "startServer")
//    public void testAPI() {
//        System.out.println("Testing API after server start");
//    }


}
