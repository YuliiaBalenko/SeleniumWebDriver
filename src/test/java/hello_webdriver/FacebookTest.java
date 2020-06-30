package hello_webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class FacebookTest {
    @Test
    public void loginToFacebook () {

        WebDriver driver = new ChromeDriver();
        String URL = "http://facebook.com";
        driver.get(URL);
        String loginId = "email";
        String login = "enot.apple@gmail.com";
        String passwordId = "pass";
        String password = "Enot2017";

        driver.findElement(By.id(loginId)).sendKeys(login);
        driver.findElement(By.id(passwordId)).sendKeys(password);

        driver.findElement(By.xpath("//input[@type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement loggedInUser = driver.findElement(By.xpath("//*[contains(@aria-label,'Главная')]"));
        Assert.assertEquals(loggedInUser.getAttribute("aria-label"),"Главная");
        driver.quit();
    }
}
