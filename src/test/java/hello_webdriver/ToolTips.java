package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ToolTips {
    WebDriver driver;
    Actions actions;
    String elementClassName = "tooltip-inner";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        String URL = "http://demoqa.com/tool-tips";
        driver.get(URL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.id("toolTipButton")));
        actions = new Actions(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void toolTipButtonTest() {

        WebElement element = driver.findElement(By.id("toolTipButton"));
        actions.moveToElement(element, 12, 6).build().perform();

        WebElement toolTipButton = waitForElementLocatedBy(driver, elementClassName);
        Assert.assertEquals(toolTipButton.getText(), "You hovered over the Button");
    }

    @Test
    public void toolTipTextFieldTest() {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.id("toolTipTextField"));
        actions.moveToElement(element, 12, 6).build().perform();

        WebElement toolTipElement = waitForElementLocatedBy(driver, elementClassName);
        Assert.assertEquals(toolTipElement.getText(), "You hovered over the text field");
    }

    @Test
    public void toolTipForContraryTest() {
        WebElement element = driver.findElement(By.xpath("//*[text()='Contrary']"));
        actions.moveToElement(element, 1, 1).build().perform();

        WebElement toolTipElement = waitForElementLocatedBy(driver, elementClassName);
        Assert.assertEquals(toolTipElement.getText(), "You hovered over the Contrary");
    }

    @Test
    public void toolTipForTextTest() {
        WebElement element = driver.findElement(By.xpath("//*[text()='1.10.32']"));
        actions.moveToElement(element, 1, 1).build().perform();

        WebElement toolTipElement = waitForElementLocatedBy(driver, elementClassName);
        Assert.assertEquals(toolTipElement.getText(), "You hovered over the 1.10.32");
    }

    private static WebElement waitForElementLocatedBy(WebDriver driver, String elementClassName) {
        return new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfElementLocated(By.className(elementClassName)));
    }
}
