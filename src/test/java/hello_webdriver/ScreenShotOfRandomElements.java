package hello_webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ScreenShotOfRandomElements {
    @Test
    public void selectRandomElements() throws IOException {
        WebDriver driver = new ChromeDriver();
        String URL = "http://demoqa.com/selectable/";
        driver.get(URL);

        List<WebElement> allElements = driver.findElements(By.xpath("//*[@id='verticalListContainer']/li"));
        for (int i = 0; i < allElements.size()-1; i++) {
            Random rand = new Random();
            int randomElement = rand.nextInt(allElements.size());
            if (!allElements.get(randomElement).getAttribute("class").contains("active")) {
                allElements.get(randomElement).click();
            }
            else
                i--;
        }
        Date d = new Date();
        String FileName = "3_elements_selected_" + d.toString().replace(":", "_").replace(" ", "_") + ".png";
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileHandler.copy(srcFile, new File("/Users/yuliiabalenko/Downloads/testScreenshot/"+FileName));
    }
}
