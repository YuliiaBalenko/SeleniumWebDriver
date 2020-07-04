package hello_webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ScreenShotOfFoundElement {
    private static String SEARCH_VALUE[] = {"selenium automation testing", "осциллограф", "абракадабрa"};
    private static final String[] SITE_NAME = {"selenium.dev", "deomera.ru", "kpi.ua"};

    @Test

    public void searchForElement() throws IOException {
        String URL = "https://google.com";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get(URL);

        /*String exePath ="/Users/yuliiabalenko/AQA/web_drivers/msedgedriver";
        System.setProperty("webdriver.edge.driver", exePath);
        driver = new EdgeDriver();
        driver.get(URL);*/


        for (int i = 0; i < SEARCH_VALUE.length; i++) {
            WebElement element = driver.findElement(By.name("q"));
            element.clear();
            element.sendKeys(Arrays.asList(SEARCH_VALUE).get(i));
            element.submit();
            ListIterator<WebElement> itr = null;
            WebElement toClick = null;

            int pageNumber = 1;
            WebDriverWait wait = new WebDriverWait(driver, 10);
            boolean flag = false;
            while (!flag) {
                List<WebElement> linkElements = wait.until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy(By.xpath("//div[@class='r']/a")));
                itr = linkElements.listIterator();
                while (itr.hasNext()) {
                    toClick = itr.next();
                    if (toClick.getAttribute("href").contains(Arrays.asList(SITE_NAME).get(i))) {
                        flag = true;
                        Date d = new Date();
                        String FileName = "EvidenceOfFoundSite_" + d.toString()
                                .replace(":", "_").replace(" ", "_") + ".png";
                        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        FileHandler.copy(srcFile, new File("/Users/yuliiabalenko/Downloads/testScreenshot/"
                                + FileName));

                        System.out.println("The found site " + Arrays.asList(SITE_NAME).get(i)
                                + " at page number: " + pageNumber);
                        break;
                    }
                }
                Boolean isPresent = driver.findElements(By.xpath("//a[@id='pnnext']/span[1]")).size() > 0;
                if (!flag & isPresent == true) {
                    driver.findElement(By.xpath("//a[@id='pnnext']/span[1]")).click();
                    pageNumber++;
                    linkElements.clear();
                } else if (isPresent == false) {
                    System.out.println("The site " + Arrays.asList(SITE_NAME).get(i)
                            + " is not found, no more Pages Available");
                    break;
                }
            }
        }
        driver.quit();

    }
}



