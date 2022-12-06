package pages;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HeaderLogoTest {
    private WebDriver driver;
    private HomePage objHomePage;
    String currentUrl;

    public HeaderLogoTest() {
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objHomePage = new HomePage(driver);
    }

    @Test
    public void clickLogoYandexOpenMainY() {
        objHomePage.clickButtonCookies();
        objHomePage.clickLogoYandex();
        new WebDriverWait(driver, 5);
        for (String tab : driver.getWindowHandles()) {
            driver.switchTo().window(tab);
        }
        currentUrl = driver.getCurrentUrl();
        Assert.assertEquals("Открылась другая страница", "https://yandex.ru/", currentUrl);
    }

    @Test
    public void clickLogoSamokatOpenMain() {
        objHomePage.clickButtonCookies();
        objHomePage.clickButtonOrder(1);
        objHomePage.clicklogoSamokat();
        this.driver.findElement(By.xpath(".//div[text() = 'Самокат ']"));
    }

    @Test
    public void clickLogoSamokatWithStatusOrder() {
        objHomePage.clickButtonCookies();
        objHomePage.clickButtonOrder(1);
        objHomePage.checkStatus("3");
        objHomePage.clicklogoSamokat();
        this.driver.findElement(By.xpath(".//div[text() = 'Самокат ']"));
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
