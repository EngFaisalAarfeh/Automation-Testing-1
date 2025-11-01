package Old_Code;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
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

        WebElement loginButton = driver.findElement(with(By.tagName("button")).below(password));
        loginButton.click();

        Thread.sleep(5000);

        driver.quit(); // Faisal new update
    }


    @Test
    public void RadioButtonsAndCheckboxes() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/samplepagetest/#contact-form-2599-2-1");
        Thread.sleep(2000);


        WebElement Graduate = driver.findElement(By.xpath("(//input[contains(@class,'radio ')])[1]"));
        Graduate.click();

        Thread.sleep(10000);

        WebElement functionalTesting = driver.findElement(By.xpath("(//input[contains(@class,'checkbox-multiple ')])[1]"));

        if (!functionalTesting.isSelected()) {
            functionalTesting.click();
        }

        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void Dropdowns() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/select-dropdown-menu/");
        Thread.sleep(2000);

        WebElement country = driver.findElement(By.tagName("select"));

        Select select = new Select(country);


        // select.selectByIndex(2); // bad code
        select.selectByValue("JOR");
        // select.selectByVisibleText("Jordan");
        country.sendKeys("Jordan");

        List<WebElement> options = select.getOptions();
        System.out.println("Total options: " + options.size());

        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void Alert() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/samplepagetest/#contact-form-2599");
        Thread.sleep(3000);

        WebElement alertButton = driver.findElement(By.xpath("//button[text()='Alert Box : Click Here']"));
        alertButton.click();

        Thread.sleep(2000);
        Alert confirmationAlert = driver.switchTo().alert();

        System.out.println("Alert Value: " + confirmationAlert.getText());
        confirmationAlert.accept();
        //   confirmationAlert.dismiss();

        Thread.sleep(1000);
        confirmationAlert.accept();
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void IFrame() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/frames-and-windows/#iFrame");
        Thread.sleep(3000);


        driver.switchTo().frame("globalSqa");

        WebElement logo = driver.findElement(By.xpath("//img[@class=' ls-is-cached lazyloaded']"));
        logo.click();
        Thread.sleep(3000);


        driver.switchTo().defaultContent(); // Leave the iframe


        driver.findElement(By.xpath("(//a[@href='https://www.globalsqa.com/about/'])[1]")).click();
        Thread.sleep(3000);

        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void MouseHover() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/frames-and-windows/#");
        Thread.sleep(3000);

        WebElement freeEBooks = driver.findElement(By.xpath("(//a[@class='no_border'])[1]"));
        Actions action = new Actions(driver);


        action.moveToElement(freeEBooks).perform();
        Thread.sleep(3000);
        driver.quit();
    }


    @Test
    public void DragAndDrop() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/draganddrop/");
        Thread.sleep(3000);

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);

        WebElement source = driver.findElement(By.xpath("//img[@alt='The peaks of High Tatras']"));
        WebElement target = driver.findElement(By.id("trash"));

        Actions action = new Actions(driver);
        action.dragAndDrop(source, target).perform();
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void RightClick_ContextClick() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(3000);

        WebElement userName = driver.findElement(By.id("username"));

        Actions action = new Actions(driver);
        action.contextClick(userName).perform();
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void doubleClick() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(3000);


        WebElement loginButton = driver.findElement(By.tagName("button"));

        Actions action = new Actions(driver);
        action.doubleClick(loginButton).perform();
        Thread.sleep(5000);
        driver.quit();
    }


    @Test
    public void KeyboardEvents() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/angularJs-protractor/registration-login-example/#/login");
        Thread.sleep(3000);

        driver.findElement(By.id("username")).sendKeys("Faisal");
        driver.findElement(By.id("password")).sendKeys("MyPassword");

        Actions action = new Actions(driver);
        Thread.sleep(1000);
        action.keyDown(Keys.TAB).perform();
        Thread.sleep(1000);
        action.keyDown(Keys.TAB).perform();

        Thread.sleep(5000);
        driver.quit();
    }

    @Test
    public void OpenNewTap() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com");
        Thread.sleep(3000);

        String originalHandle = driver.getWindowHandle();
        System.out.println("Original tab handle: " + originalHandle);

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.google.com");
        System.out.println("New tab opened and navigated to: " + driver.getTitle());

        Thread.sleep(3000);

        driver.switchTo().window(originalHandle);
        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void FileUpLoad() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/samplepagetest/");
        Thread.sleep(3000);
        WebElement fileUpload = driver.findElement(By.name("file-553"));

        fileUpload.sendKeys("C:/Users/enghi/Desktop/JMeter/JMTest.png");


        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void FullScreenShot() throws InterruptedException, IOException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com");
        Thread.sleep(3000);


        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(src, new File("Screenshots/homePage.png"));

        Thread.sleep(3000);
        driver.quit();
    }

    @Test
    public void ElementScreenShot() throws InterruptedException, IOException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com");
        Thread.sleep(3000);

        WebElement logo = driver.findElement(By.id("logo"));
        File file = logo.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("Screenshots/logo.png"));

        Thread.sleep(3000);
        driver.quit();
    }


    @Test
    public void HardAssertions() throws InterruptedException, IOException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com");
        Thread.sleep(3000);

        WebElement logo = driver.findElement(By.id("logo"));
        File file = logo.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("Screenshots/logo.png"));

        Thread.sleep(3000);
        driver.quit();
    }


    @Test
    public void Sleep() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");
        Thread.sleep(3000);

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);
        driver.findElement(By.id("downloadButton")).click();
        Thread.sleep(7000);

        System.out.println("Results: " + driver.findElement(By.className("progress-label")).getText());

        driver.close();

    }


    @Test
    public void Sleep2() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);
        driver.findElement(By.id("downloadButton")).click();

        System.out.println("Results: " + driver.findElement(By
                .xpath("//div[text()='Complete!']")).getText());
        driver.close();

    }


    @Test
    public void Sleep3() throws InterruptedException {

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("downloadButton")));
        element.click();

        WebElement element_2 = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[text()='Complete!']")));

        System.out.println("Results: " + element_2.getText());
        driver.close();

    }


    @Test
    public void Sleep4() throws InterruptedException {

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(20))// max wait time
                .pollingEvery(Duration.ofMillis(500)) // check for element every 500ms
                .ignoring(NoSuchElementException.class) // ignore this exception
                .ignoring(IOException.class) // ignore this one too
                .ignoring(ElementNotInteractableException.class); // ignore this one too

        WebElement element = fluentWait.until(driver -> driver.findElement(By.id("downloadButton")));
        element.click();

        WebElement element_2 = fluentWait.until(driver -> driver.findElement(By.xpath("//div[text()='Complete!']")));

        System.out.println("Results: " + element_2.getText());
        driver.close();

    }



    @Test
    public void Implicit_Wait() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);

        driver.findElement(By.id("downloadButton")).click();

        System.out.println("Results: " +
                driver.findElement(By.xpath("//div[text()='Complete!']")).getText());
         driver.close();

    }




    @Test
    public void Explicit_Wait() {

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.id("downloadButton")));

        element.click();

        WebElement element_Complete = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//div[text()='Complete!']")));

        System.out.println("Results: " +
                element_Complete.getText());
        driver.close();

    }



    @Test
    public void Fluent_Wait() {

        driver.manage().window().maximize();
        driver.get("https://www.globalsqa.com/demo-site/progress-bar/");

        WebElement iframeElement = driver.findElement(By.className("demo-frame"));
        driver.switchTo().frame(iframeElement);

        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(7)) // max time to wait
                .pollingEvery(Duration.ofMillis(500)) // check for element every 500ms

                .ignoring(NoSuchElementException.class) // skip this exception
                .ignoring(ElementNotInteractableException.class);

         WebElement element = fluentWait.until(driver -> driver.findElement(By.id("downloadButton")));
          element.click(); // bad code

        WebElement element_Complete = fluentWait.until(
                driver -> driver.findElement(By.xpath("//div[text()='Complete!']")));

        System.out.println("Results: " + element_Complete.getText());
        driver.close();

        Assert.assertEquals("ss", "Complete!");
    }


}
