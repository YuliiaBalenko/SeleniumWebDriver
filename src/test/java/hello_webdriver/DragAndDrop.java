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
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DragAndDrop {
    String elements = "One\nTwo\nThree\nFour\nFive\nSix";
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        String URL = "http://demoqa.com/sortable/";
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkSortingOfElements() {
        List<WebElement> actualElements = driver.findElements(By.xpath("//*[@id='demo-tabpane-list']/div"));
        for(WebElement ele:actualElements) {
            String element_name = ele.getText();
            Assert.assertEquals(element_name, elements);
        }
    }

    @Test
    public void dragAndDrop() {

        WebElement From = driver.findElement(By.xpath("//*[@class='vertical-list-container mt-4']/div[2]"));
        WebElement To = driver.findElement(By.xpath("//*[@class='vertical-list-container mt-4']/div[5]"));

        Actions act = new Actions(driver);
        act.dragAndDrop(From, To).build().perform();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

}
