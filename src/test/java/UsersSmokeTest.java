import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class UsersSmokeTest {
    public static WebDriver driver;

    @Test
    public void updateOwnProfile() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/user/Desktop/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Login
        driver.get("http://users.bugred.ru/user/login/index.html");
        driver.findElement(By.name("login")).sendKeys("random1@test.com");
        driver.findElement(By.xpath("//form[@action='/user/login/index.html']//input[@name='password']")).sendKeys("random1");
        driver.findElement(By.xpath("//div[@class='row']//div[@class='col-md-6'][1]//input[@type='submit']")).click();

        WebElement addUserButton = driver.findElement(By.xpath("//a[text() = 'Добавить пользователя']"));
        Assert.assertEquals(true, addUserButton.isDisplayed());

        // Update profile
        String newName = "random11";
        driver.findElement(By.xpath("//a[@class='dropdown-toggle']")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Личный кабинет')]")).click();
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys(newName);
        new Select(driver.findElement(By.name("gender"))).selectByVisibleText("Женский");
        driver.findElement(By.name("birthday")).sendKeys("01011990");
        driver.findElement(By.name("date_start")).sendKeys("01012020");
        driver.findElement(By.name("hobby")).clear();
        driver.findElement(By.name("hobby")).sendKeys("my_new_hobby");
        driver.findElement(By.name("inn")).sendKeys("123451234512");
        driver.findElement(By.name("act_profile_now")).click();

        driver.findElement(By.xpath("//div[@id='main-menu']//li[@class='newlink']//a[@href='/']")).click();

        driver.findElement(By.name("q")).sendKeys(newName);
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        int newUserNumber = driver.findElements(By.xpath("//tbody[@class='ajax_load_row']//tr")).size();
        Assert.assertEquals(true, newUserNumber > 0);

        driver.quit();
    }
}