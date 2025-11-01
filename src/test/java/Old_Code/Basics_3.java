package Old_Code;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class Basics_3 {
    WebDriver driver;
    SoftAssert softAssert;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        softAssert = new SoftAssert();
    }

    @Test
    public void hardAssertionsExample() throws InterruptedException {
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(2000);

        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(2000);

        // Hard assertions (test stops at first failure)
        String logo = driver.findElement(By.className("app_logo")).getText();
        Assert.assertEquals(logo, "Swag Labs", "Logo text mismatch");

        // Continue with other assertions if the first one passes
        boolean isCartDisplayed = driver.findElement(By.className("shopping_cart_link")).isDisplayed();
        Assert.assertTrue(isCartDisplayed, "Shopping cart should be visible");
    }

    @Test
    public void softAssertionsExample() {
        // Navigate to the test page
        driver.get("https://www.saucedemo.com/");

        // Test 1: Verify page title
        String actualTitle = driver.getTitle();
        softAssert.assertTrue(actualTitle.contains("Swag Labs"),
                "Page title should contain 'Swag Labs'");

        // Test 2: Login with valid credentials
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Test 3: Verify login was successful
        String currentUrl = driver.getCurrentUrl();
        softAssert.assertTrue(currentUrl.contains("inventory.html"),
                "After login, should be redirected to inventory page");

        // Test 4: Verify logo text
        String logoText = driver.findElement(By.className("app_logo")).getText();
        softAssert.assertEquals(logoText, "Swag Labs",
                "Logo text should be 'Swag Labs'");

        // Test 5: Verify shopping cart is present
        boolean isCartDisplayed = driver.findElement(By.className("shopping_cart_link")).isDisplayed();
        softAssert.assertTrue(isCartDisplayed,
                "Shopping cart should be displayed after login");

        // Test 6: Verify there are products displayed
        int productCount = driver.findElements(By.className("inventory_item")).size();
        softAssert.assertTrue(productCount > 0,
                "There should be products displayed on the page");

        // Test 7: Click on first product and verify details page
        driver.findElement(By.xpath("(//div[@class='inventory_item_name '])[1]")).click();
        String productTitle = driver.findElement(By.className("inventory_details_name")).getText();
        softAssert.assertNotNull(productTitle,
                "Product title should be displayed on details page");

        // This will report all assertion failures at the end
        softAssert.assertAll("Test completed with the following failures:");
    }

    @Test
    public void clickProductByIndexExample() throws InterruptedException {
        // Navigate to the login page
        driver.get("https://www.saucedemo.com/");
        
        // Login
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        
        Thread.sleep(4000);


        List<WebElement> products =
            driver.findElements(By.className("inventory_item_name"));

        Thread.sleep(2000);
        products.get(4).click();

        Thread.sleep(5000);

    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
