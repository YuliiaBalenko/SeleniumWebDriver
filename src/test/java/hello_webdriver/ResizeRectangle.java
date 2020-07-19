package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class ResizeRectangle {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String URL = "http://demoqa.com/resizable/";
        driver.get(URL);

        WebElement element = driver.findElement(By.id("resizableBoxWithRestriction"));
        Dimension elementDimensions = element.getSize();
        System.out.println("Initial Height: " + elementDimensions.height + " Initial Width: " + elementDimensions.width);

        WebElement resizeElement = driver.findElement(By.xpath("//*[@id='resizableBoxWithRestriction']/span"));
        Actions actionsBigger = new Actions(driver);
        actionsBigger.dragAndDropBy(resizeElement, 100, 100).perform();
        Dimension biggerElementDimensions = element.getSize();
        System.out.println("Height after increase: " + biggerElementDimensions.height + " Width after increase: " + biggerElementDimensions.width);

        Actions actionsSmaller = new Actions(driver);
        actionsSmaller.dragAndDropBy(resizeElement, -200, -200).perform();
        Dimension smallerElementDimentions = element.getSize();
        System.out.println("Height after decrease: " + smallerElementDimentions.height + " Width after decrease: " + smallerElementDimentions.width);
        driver.quit();
    }
}
