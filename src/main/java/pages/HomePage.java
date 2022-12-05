package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
    private int divNumber;
    private By list;
    private By listOpen;
    private By buttonCookies = By.id("rcc-confirm-button");
    private By buttonOrderHeader = By.className("Button_Button__ra12g");
    private By buttonOrderRoadMap = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/button");
    private By logoYandex = By.className("Header_LogoYandex__3TSOI");
    private By logoSamokat = By.className("Header_LogoScooter__3lsAR");
    private By buttonOrderStatus = By.className("Header_Link__1TAG7");
    private By ordersNumberField = By.xpath(".//div/input[@placeholder = 'Введите номер заказа']");
    private By buttonGo = By.xpath(".//button[text() = 'Go!']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickButtonCookies() {
        driver.findElement(buttonCookies).click();
    }

    public void clicklogoYandex() {
        driver.findElement(logoYandex).click();
    }

    public void clicklogoSamokat() {
        driver.findElement(logoSamokat).click();
    }

    public void scrollToList(int divNumber) {
        this.listOpen = By.xpath(String.format(".//div[@id='accordion__panel-%x']/p", divNumber - 1));
        WebElement element = driver.findElement(listOpen);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickList(int divNumber) {
        this.list = By.xpath(String.format(".//div[@class='accordion']/div[%x]", divNumber));
        driver.findElement(list).click();
        new WebDriverWait(this.driver, 3L);
    }

    public String getTextList(int divNumber) {
        this.listOpen = By.xpath(String.format(".//div[@id='accordion__panel-%x']", divNumber - 1));
        return driver.findElement(listOpen).getText();
    }

    public void clickButtonOrder(int button) {
        if (button == 1) {
            driver.findElement(buttonOrderHeader).click();
        } else {
            driver.findElement(buttonOrderRoadMap).click();
        }

    }

    public void scrollToButtonOrder(int button) {
        WebElement element;
        if (button == 1) {
            element = driver.findElement(buttonOrderHeader);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        } else {
            element = driver.findElement(buttonOrderRoadMap);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        }

    }

    public void clickButtonOrderStatus() {
        driver.findElement(buttonOrderStatus).click();
    }

    public void setOrdersNumber(String number) {
        new WebDriverWait(driver,3).
        until(ExpectedConditions.elementToBeClickable(ordersNumberField));
        driver.findElement(ordersNumberField).clear();
        driver.findElement(ordersNumberField).sendKeys(number);
    }

    public void clickButtonGo() {
       driver.findElement(buttonGo).click();
    }

    public void checkStatus(String number) {
        clickButtonOrderStatus();
        setOrdersNumber(number);
        clickButtonGo();
    }
}