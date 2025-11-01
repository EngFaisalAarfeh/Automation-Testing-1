package Old_Code;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.List;


public class Basics_2 {

    WebDriver driver = new ChromeDriver();

    @Test
    public void  HardAssertions () throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        String logo = driver.findElement(By.className("app_logo")).getText();

        Assert.assertEquals(logo, "Swag Labs", "Logo is not right");
        //Assert.assertEquals(logo, driver.findElement(By.className("app_logo")).getText(), "Logo is not right");

        driver.findElement(By.xpath("(//div[@class='inventory_item_name '])[1]")).click();




        //  Assert.assertTrue(logo.contains("Swag"),"Logo is not right");
        // Assert.assertFalse(logo.contains("Swag"),"Logo is not right");
        // Assert.assertNotEquals(logo, "Swag Labs", "Logo is not right");
        //  Assert.fail(" fail ");
        System.out.println("Results: "+ logo.contains("Swag"));

        Thread.sleep(1000);
//        String expectedTitle = "My Products";
//        String actualTitle = driver.getTitle();

//        Assert.assertEquals(actualTitle, expectedTitle);
        driver.quit();
    }


    @Test
    public void  SoftAssertions () throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000);

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);

        SoftAssert softAssert = new SoftAssert();



        String logo = driver.findElement(By.className("app_logo")).getText();

        softAssert.assertEquals(logo,"Swag Labs","Home Page title");

        driver.findElement(By.xpath("(//div[@class='inventory_item_name '])[1]")).click();

        softAssert.assertEquals(driver.findElement(By.xpath(
                "//div[@class='inventory_details_name large_size']")).getText()
                ,"Sauce Labs Backpack","Page title");


        softAssert.assertTrue(driver.findElement(By.xpath(
                        "//div[@class='inventory_details_name large_size']"))
                .getText().contains("Backpack"));

        Thread.sleep(1000);

        driver.quit();
        softAssert.assertAll();
    }




    @Test
    public void  SoftAssertions_Wait () {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");

        driver.findElement(By.id("username")).sendKeys("Test");
        driver.findElement(By.id("password")).sendKeys("TestPassword");
        driver.findElement(By.tagName("button")).click();


        driver.quit();
    }


    @Test
    public void  SoftAssertions_Wait2 () throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));


        element.sendKeys("Faisal");
        driver.findElement(By.id("password")).sendKeys("TestPassword");
        driver.findElement(By.tagName("button")).click();

        Thread.sleep(10000);
        driver.quit();
    }



    @Test
    public void  findElements () throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(3000); // Hard-coded pause - Bad Code

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(4000);


        List<WebElement> products =
                driver.findElements(By.className("inventory_item_name"));
        products.get(3).click();
        Thread.sleep(4000);

    }






    @Test
    public void  implicitlyWait () throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        List<WebElement> products =
                driver.findElements(By.className("inventory_item_name"));
        products.get(3).click();

        driver.close();
    }




    @Test
    public void  Explicit_Wait () {

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");


WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("downloadButton")));
  element.click();


  driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();


        List<WebElement> products =
                driver.findElements(By.className("inventory_item_name"));
        products.get(3).click();

        driver.close();
    }



}
