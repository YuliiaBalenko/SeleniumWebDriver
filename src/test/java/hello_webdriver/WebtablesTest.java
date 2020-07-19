package hello_webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebtablesTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        String URL = "http://demoqa.com/webtables";
        driver.get(URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void addPersonModifyAge() {

        String addButtonId = "addNewRecordButton";
        String firstName = "Yuliia";
        String lastName = "Balenko";
        String email = "example@gmail.com";
        String age = "26";
        String modifiedAge = "30";
        String salary = "10000";
        String department = "IT";
        String editLastPersonXpath = "(//span[@title='Edit'])[last()]";

        driver.findElement(By.id(addButtonId)).click();

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);
        driver.findElement(By.id("age")).sendKeys(age);
        driver.findElement(By.id("salary")).sendKeys(salary);
        driver.findElement(By.id("department")).sendKeys(department);

        driver.findElement(By.id("submit")).click();

        driver.findElement(By.xpath(editLastPersonXpath)).click();
        driver.findElement(By.id("age")).clear();
        driver.findElement(By.id("age")).sendKeys(modifiedAge);
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.xpath(editLastPersonXpath)).click();
        String newAge = driver.findElement(By.id("age")).getAttribute("value");
        Assert.assertEquals(newAge, modifiedAge);
    }

    @Test
    public void deleteLastPerson() {
        String deleteLastPersonXpath = "(//span[@title='Delete'])[last()]";
        driver.findElement(By.xpath(deleteLastPersonXpath)).click();
    }
}
