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
    private HomePage objHomePage;
    private Order objOrder;
    private AboutRent objAboutRent;

    public OrderTest(int numberButton,
                     String name, String surname, String address, String metro, String phone,
                     String yearMonth, int day, int period, int option, String comment) {
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
    public void submitOrder(){
        objAboutRent.clickButtonOrder();
        objAboutRent.clickButtonYes();
        driver.findElement(By.xpath(".//div[@class = 'Order_Modal__YZ-d3']/div[text() = 'Заказ оформлен']"));
    }
    public void startOrdering(int numberButton,
                              String name,String surname,String address, String metro, String phone){
        objHomePage.clickButtonCookies();
        objHomePage.scrollToButtonOrder(numberButton);
        objHomePage.clickButtonOrder(numberButton);
        objOrder.setOrderData(name, surname, address, metro, phone);
    }
    public void clickButtonsBackFurther() {
        objAboutRent.clickButtonBack();
        objOrder.clickButtonFurther();
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        objHomePage = new HomePage(driver);
        objOrder = new Order(driver);
        objAboutRent = new AboutRent(driver);
    }

    @Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {1,
                        "Вася", "Пупкин", "Москва", "Бульвар Рокоссовского", "790000000000",
                        "2022-12", 1, 1, 1, "No comments"},
                {2,
                        "Петя", "Тестов", "Москва", "Лихоборы", "790000000000",
                        "2022-12", 31, 6, 2, ""}
        };
    }

    @Test
    public void checkOrder() {
        startOrdering(numberButton,name,surname,address, metro,phone);
        objAboutRent.setAboutRent(yearMonth, day, period, option, comment);
        submitOrder();
    }

    @Test
    public void checkNegative() {
        startOrdering(numberButton,name,surname,address, metro,phone);
        clickButtonsBackFurther();
        objAboutRent.setAboutRent(yearMonth, day, period, option, comment);
        clickButtonsBackFurther();
        objAboutRent.clickButtonOrder();
        objAboutRent.clickButtonNo();
        submitOrder();
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}