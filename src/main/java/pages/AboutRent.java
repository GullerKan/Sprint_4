package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AboutRent {
    private WebDriver driver;
    private int option;
    private String yearMonth;
    private int day;
    private int period;
    private By datePicker;
    private By dateField = By.className("Order_MixedDatePicker__3qiay");
    private By periodField = By.className("Dropdown-root");
    private By periodOption;
    private By color;
    private By commentField = By.xpath(".//div[@class = 'Input_InputContainer__3NykH']/input[@placeholder = 'Комментарий для курьера']");
    private By backButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Назад']");
    private By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");
    private By noButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Нет']");
    private By yesButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Да']");

    public AboutRent(WebDriver driver) {
        this.driver = driver;
    }

    public void clickData(String yearMonth, int day) {
        this.yearMonth = yearMonth;
        this.day = day;
        this.datePicker = By.xpath(String.format(".//div[@aria-label='month  %s']/div/div[text()='%s']", yearMonth, day));
        driver.findElement(dateField).click();
        driver.findElement(datePicker).click();
    }

    public void clickPeriod(int period) {
        this.period = period;
        this.periodOption = By.xpath(String.format(".//div[@class = 'Dropdown-menu']/div[%x]", period));
        driver.findElement(periodField).click();
        driver.findElement(periodOption).click();
    }

    public void chooseColor(int option) {
        this.option = option;
        this.color = By.xpath(String.format(".//div[@class='Order_Checkboxes__3lWSI']/label[%x]", option));
        driver.findElement(color).click();
    }

    public void setComment(String comment) {
        driver.findElement(commentField).clear();
        driver.findElement(commentField).sendKeys(new CharSequence[]{comment});
    }

    public void clickButtonBack() {
        driver.findElement(backButton).click();
    }

    public void clickButtonOrder() {
        driver.findElement(orderButton).click();
    }

    public void clickButtonNo() {
        driver.findElement(noButton).click();
    }

    public void clickButtonYes() {
        (new WebDriverWait(this.driver, 3L)).until(ExpectedConditions.elementToBeClickable(yesButton));
        driver.findElement(yesButton).click();
    }

    public void setAboutRent(String yearMonth, int day, int period, int option, String comment) {
        clickData(yearMonth, day);
        clickPeriod(period);
        chooseColor(option);
        setComment(comment);
    }
}
