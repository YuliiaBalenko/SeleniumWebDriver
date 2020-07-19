package hello_webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FindUrlInGoogleSearch {
    WebDriver driver;
    List<WebElement> linkElements;
    Boolean nextPage;
    int pageNumber = 1;

    @BeforeMethod
    public void setUp() {
        String URL = "https://google.com";
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"selenium automation testing", "www.selenium.dev"},
                {"осциллограф", "deomera.ru"},
                {"абракадабрa", "kpi.ua"}};
    }

    @Test(dataProvider = "data-provider")
    public void searchForUrl(String searchValue, String url) throws IOException {

        String linkElementsLocator = "//div[@class='r']/a";
        WebElement googleSearchField = driver.findElement(By.name("q"));
        googleSearchField.sendKeys(searchValue);
        googleSearchField.submit();
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(linkElementsLocator)));

        Boolean urlIsFound = false;
        while (urlIsFound == false) {
            linkElements = driver.findElements(By.xpath(linkElementsLocator));
            for (WebElement link : linkElements) {
                String linkName = link.getText();
                if (linkName.contains(url)) {
                    urlIsFound = true;

                    //take screenshot of found URL
                    Date d = new Date();
                    String FileName = "EvidenceOfFoundUrl_" + d.toString()
                            .replace(":", "_").replace(" ", "_") + ".png";
                    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                    FileHandler.copy(srcFile, new File("/Users/yuliiabalenko/Downloads/testScreenshot/"
                            + FileName));

                    System.out.println("The found URL " + url + " at page number: " + pageNumber);
                    break;
                }
            }

            String nextPageXpath = "//a[@id='pnnext']/span[1]";
            nextPage = driver.findElements(By.xpath(nextPageXpath)).size() > 0;
            if (nextPage == true & urlIsFound == false) {
                driver.findElement(By.xpath(nextPageXpath)).click();
                pageNumber++;
            } else if (nextPage == false) {
                System.out.println("The site " + url + " is not found, no more Pages Available");
                break;
            }
        }
    }
}




