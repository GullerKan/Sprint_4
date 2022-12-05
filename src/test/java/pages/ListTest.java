package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class ListTest {
    private WebDriver driver;
    int divNumber;
    String expected;

    public ListTest(int divNumber, String expected) {
        this.divNumber = divNumber;
        this.expected = expected;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameters
    public static Object[][] getTestData() {
        return new Object[][]{{1, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."}, {2, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."}, {3, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."}, {4, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."}, {5, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."}, {6, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."}, {7, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."}, {8, "Да, обязательно. Всем самокатов! И Москве, и Московской области."}};
    }

    @Test
    public void checkList() {
        this.driver = new ChromeDriver();
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(this.driver);
        objHomePage.clickButtonCookies();
        objHomePage.clickList(this.divNumber);
        objHomePage.scrollToList(this.divNumber);
        Assert.assertEquals("Текст не совпал", this.expected, objHomePage.getTextList(this.divNumber));
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
