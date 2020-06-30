package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebtablesTest {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        String URL = "http://demoqa.com/webtables";
        driver.get(URL);

        driver.findElement(By.id("addNewRecordButton")).click();

        String firstName = "Yuliia";
        String lastName = "Balenko";
        String email = "example@gmail.com";
        String age = "26";
        String modifiedAge = "30";
        String salary = "10000";
        String department = "IT";

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("age")).sendKeys(age);
        driver.findElement(By.id("salary")).sendKeys(salary);
        driver.findElement(By.id("department")).sendKeys(department);

        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("edit-record-4")).click();
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(modifiedAge);
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("delete-record-4")).click();
        driver.quit();

    }
}
