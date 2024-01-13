package test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class header extends loaderPage {
    private WebDriver driver;

    public header(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By menuAllApp = By.xpath("//i[contains(@class,'icon-menu-ellipsis')]//ancestor-or-self::button");

    private By accountMenu = By.xpath("//div[@data-testid='header_btn_switch_user']");

    private By switchAccount = By.xpath("//li[normalize-space() = 'Switch Account']");

    public By itemSelected(int index){
        return By.xpath("//div[@data-testid = 'menu_item_" + index +"_lbl']");
    }

    public void clickSwitchCompany(){
        driver.findElement(accountMenu).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(switchAccount));
        WebElement switchAcc = driver.findElement(switchAccount);
        switchAcc.click();
    }
    public String getCurrentCompany() {
        WebElement currentAccount = driver.findElement(By.xpath("//div[@data-testid = 'header_lbl_company_name']//span"));
        return currentAccount.getText();
    }

    public void searchCompany(String name){
        waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]");
        WebElement txtSearch = driver.findElement(By.xpath("//div[@class = 'el-input el-input--prefix']//input"));
        txtSearch.sendKeys(name);
    }

    public void switchCompany(String name){
        if (!name.equals(getCurrentCompany())) {
            clickSwitchCompany();
            searchCompany(name);
            waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]");
            WebElement accName = driver.findElement(By.xpath("//span[normalize-space() = '" + name + "']"));
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(accName));
            accName.click();
        }
    }

    public void clickAllApps(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(menuAllApp));
        driver.findElement(menuAllApp).click();
    }
    public void clickMyJobs(){
        waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]");
        clickAllApps();
        WebElement myJobs = driver.findElement(itemSelected(2));
        myJobs.click();
    }

    public void clickSetting(){
        waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]" );
        clickAllApps();
        driver.findElement(itemSelected(31)).click();
    }

}
