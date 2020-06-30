package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class DragAndDrop {
    @Test
    public void DradnDrop() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        String URL = "http://demoqa.com/sortable/";
        driver.get(URL);

        WebElement From = driver.findElement(By.xpath("//*[@class='vertical-list-container mt-4']/div[2]"));
        WebElement To = driver.findElement(By.xpath("//*[@class='vertical-list-container mt-4']/div[5]"));

        Actions act = new Actions(driver);
        act.dragAndDrop(From, To).build().perform();
        Thread.sleep(2000);
        driver.quit();
    }

}
