package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Order {
    private WebDriver driver;
    private By nameField = By.xpath(".//div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Имя']");
    private By surnameField = By.xpath(".//div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Фамилия']");
    private By addressField = By.xpath(".//div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Адрес: куда привезти заказ']");
    private By metroField = By.className("select-search__input");
    private By phoneField = By.xpath(".//div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = '* Телефон: на него позвонит курьер']");
    private By furtherButton = By.xpath(".//button[text()='Далее']");

    public Order(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String name) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    public void setSurname(String surname) {
        driver.findElement(surnameField).clear();
        driver.findElement(surnameField).sendKeys(surname);
    }

    public void setAddress(String address) {
        driver.findElement(addressField).clear();
       driver.findElement(addressField).sendKeys(address);
    }

    public void setMetro(String metro) {
        driver.findElement(metroField).clear();
        driver.findElement(metroField).sendKeys(metro);
        WebElement element = driver.findElement(By.xpath(String.format(".//div[text()='%s']", metro)));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath(String.format(".//div[text()='%s']", metro))).click();
    }

    public void setPhone(String phone) {
        driver.findElement(phoneField).clear();
        driver.findElement(phoneField).sendKeys(phone);
    }

    public void clickButtonFurther() {
        driver.findElement(furtherButton).click();
    }

    public void setOrderData(String name, String surname, String address, String metro, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
        clickButtonFurther();
    }
}