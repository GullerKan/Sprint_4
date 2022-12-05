package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private int numberButton;
    private String name;
    private String surname;
    private String address;
    private String metro;
    private String phone;
    private String yearMonth;
    private int day;
    private int period;
    private int option;
    private String comment;

    public OrderTest(int numberButton, String name, String surname, String address, String metro, String phone, String yearMonth, int day, int period, int option, String comment) {
        this.numberButton = numberButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.yearMonth = yearMonth;
        this.day = day;
        this.period = period;
        this.option = option;
        this.comment = comment;
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
    }

    @Parameters
    public static Object[][] getTestData() {
        return new Object[][]{{1, "Вася", "Пупкин", "Москва", "Бульвар Рокоссовского", "790000000000", "2022-12", 1, 1, 1, "No comments"}, {2, "Петя", "Тестов", "Москва", "Лихоборы", "790000000000", "2022-12", 31, 6, 2, ""}};
    }

    @Test
    public void checkOrder() {
        this.driver = new ChromeDriver();
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(this.driver);
        Order objOrder = new Order(this.driver);
        AboutRent objAboutRent = new AboutRent(this.driver);
        objHomePage.clickButtonCookies();
        objHomePage.scrollToButtonOrder(this.numberButton);
        objHomePage.clickButtonOrder(this.numberButton);
        objOrder.setOrderData(this.name, this.surname, this.address, this.metro, this.phone);
        objAboutRent.setAboutRent(this.yearMonth, this.day, this.period, this.option, this.comment);
        objAboutRent.clickButtonOrder();
        objAboutRent.clickButtonYes();
        //this.driver.findElement(By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div[text() = 'Заказ оформлен']"));
    }

    @Test
    public void checkNegative() {
        this.driver = new ChromeDriver();
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePage objHomePage = new HomePage(this.driver);
        Order objOrder = new Order(this.driver);
        AboutRent objAboutRent = new AboutRent(this.driver);
        objHomePage.clickButtonCookies();
        objHomePage.scrollToButtonOrder(this.numberButton);
        objHomePage.clickButtonOrder(this.numberButton);
        objOrder.setOrderData(this.name, this.surname, this.address, this.metro, this.phone);
        objAboutRent.clickButtonBack();
        objOrder.clickButtonFurther();
        objAboutRent.setAboutRent(this.yearMonth, this.day, this.period, this.option, this.comment);
        objAboutRent.clickButtonBack();
        objOrder.clickButtonFurther();
        objAboutRent.clickButtonOrder();
        objAboutRent.clickButtonNo();
        objAboutRent.clickButtonOrder();
        objAboutRent.clickButtonYes();
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}