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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DragAndDrop {
    WebDriver driver;
    List<String> expectedElements = Arrays.asList("One", "Two", "Three", "Four", "Five", "Six");
    ArrayList<String> actualElements = new ArrayList<>();
    String allElementsXpath = "//*[@id='demo-tabpane-list']/div/div";

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        String URL = "http://demoqa.com/sortable/";
        driver.get(URL);
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allElementsXpath)));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkSortingOfElements() {
        List<WebElement> elements = driver.findElements(By.xpath(allElementsXpath));
        for (WebElement ele : elements) {
            String elementName = ele.getText();
            actualElements.add(elementName);
        }
        Assert.assertEquals(actualElements, expectedElements);
    }

    @Test
    public void dragAndDrop() {
        String fromElementXpath = "//*[@class='vertical-list-container mt-4']/div[2]";
        String toElementXpath = "//*[@class='vertical-list-container mt-4']/div[5]";

        WebElement from = driver.findElement(By.xpath(fromElementXpath));
        WebElement to = driver.findElement(By.xpath(toElementXpath));

        Actions act = new Actions(driver);
        act.dragAndDrop(from, to).build().perform();
    }

}
