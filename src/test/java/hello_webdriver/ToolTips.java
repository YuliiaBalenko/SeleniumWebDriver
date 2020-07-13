package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ToolTips {
    WebDriver driver;
    Actions actions;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        String URL = "http://demoqa.com/tool-tips";
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void toolTipButtonTest() {

        WebElement element = driver.findElement(By.id("toolTipButton"));
        actions.moveToElement(element).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement toolTipButton = driver.findElement(By.className("tooltip-inner"));
        Assert.assertEquals(toolTipButton.getText(), "You hovered over the Button");

    }

    @Test
    public void toolTipTextFieldTest() {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.id("toolTipTextField"));
        actions.moveToElement(element).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement toolTipElement = driver.findElement(By.className("tooltip-inner"));
        Assert.assertEquals(toolTipElement.getText(), "You hovered over the text field");

    }

    @Test
    public void toolTipForContraryTest() {
        WebElement element = driver.findElement(By.xpath("//*[text()='Contrary']"));
        actions.moveToElement(element).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement toolTipElement = driver.findElement(By.className("tooltip-inner"));
        Assert.assertEquals(toolTipElement.getText(), "You hovered over the Contrary");

    }

    @Test
    public void toolTipForTextTest() {
        WebElement element = driver.findElement(By.xpath("//*[text()='1.10.32']"));
        actions.moveToElement(element).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement toolTipElement = driver.findElement(By.className("tooltip-inner"));
        Assert.assertEquals(toolTipElement.getText(), "You hovered over the 1.10.32");

    }

}
