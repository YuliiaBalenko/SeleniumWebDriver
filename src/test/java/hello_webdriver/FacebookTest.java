package hello_webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class FacebookTest {
    WebDriver driver;


    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        String URL = "http://facebook.com";
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void loginToFacebook() {

        String loginId = "email";
        String login = "enot.apple@gmail.com";
        String passwordId = "pass";
        String password = "Enot2017";
        String logInButton = "//input[@type='submit']";
        String mainContentLocator = "//*[@class='_6s5d _71pn _-kb sf']";

        driver.findElement(By.id(loginId)).sendKeys(login);
        driver.findElement(By.id(passwordId)).sendKeys(password);
        driver.findElement(By.xpath(logInButton)).click();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(mainContentLocator)));

        WebElement loggedInUser = driver.findElement(By.xpath(mainContentLocator));
        boolean status = loggedInUser.isDisplayed();
        Assert.assertEquals(status, true);
    }

}
