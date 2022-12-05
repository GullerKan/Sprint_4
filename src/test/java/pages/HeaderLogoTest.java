package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.Iterator;
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
    String currentUrl;

    public HeaderLogoTest() {
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void clickLogoYandexOpenMainY() {
        this.driver = new ChromeDriver();
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(this.driver);
        objHomePage.clickButtonCookies();
        objHomePage.clicklogoYandex();
        new WebDriverWait(this.driver, 5L);
        Iterator var2 = this.driver.getWindowHandles().iterator();

        while(var2.hasNext()) {
            String tab = (String)var2.next();
            this.driver.switchTo().window(tab);
        }

        this.currentUrl = this.driver.getCurrentUrl();
        Assert.assertEquals("Открылась другая страница", "https://yandex.ru/", this.currentUrl);
    }

    @Test
    public void clickLogoSamokatOpenMain() {
        this.driver = new ChromeDriver();
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(this.driver);
        objHomePage.clickButtonCookies();
        objHomePage.clickButtonOrder(1);
        objHomePage.clicklogoSamokat();
        this.driver.findElement(By.xpath(".//div[text() = 'Самокат ']"));
    }

    @Test
    public void clickLogoSamokatWithStatusOrder() {
        this.driver = new ChromeDriver();
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(this.driver);
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
