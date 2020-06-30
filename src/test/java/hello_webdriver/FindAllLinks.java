package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.util.List;

public class FindAllLinks {
    public static void  main(String[] args) throws InterruptedException {
        String exePath ="/Users/yuliiabalenko/AQA/web_drivers/msedgedriver";
        System.setProperty("webdriver.edge.driver", exePath);
        WebDriver driver = new EdgeDriver();
        String URL = "https://en.wikipedia.org/wiki/Main_Page";
        driver.get(URL);
        List<WebElement> links = driver.findElements(By.tagName("a"));
        System.out.println("Total number of links: "+links.size());
        System.out.println("List of links:");
        for (int i=0; i<links.size(); i++)
        {
            System.out.println(i+" " +links.get(i).getText());
        }
        Thread.sleep(2000);
        driver.quit();

    }
}
