package test.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class header extends common {
    private WebDriver driver;
    private By currentAccount = By.xpath("//div[@data-testid = 'header_lbl_company_name']//span");

    private By txtSearch = By.xpath("//div[@class = 'el-input el-input--prefix']//input");

    private Integer myJobIndex = 2;
    private Integer settingIndex = 31;



    public header(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    private By menuAllApp = By.xpath("//i[contains(@class,'icon-menu-ellipsis')]//ancestor-or-self::button");

    private By accountMenu = By.xpath("//div[@data-testid='header_btn_switch_user']");

    private By switchAccount = By.xpath("//li[normalize-space() = 'Switch Account']");

    public By itemSelected(int index){
        return By.xpath("//div[@data-testid = 'menu_item_" + index +"_lbl']");
    }

    public void clickSwitchCompany(){
        driver.findElement(accountMenu).click();
        waiter.until(ExpectedConditions.elementToBeClickable(switchAccount));
        driver.findElement(switchAccount).click();
        waitForPageLoading();
    }
    public String getCurrentCompany() {
        return driver.findElement(currentAccount).getText();
    }

    public void searchCompany(String name){
        driver.findElement(txtSearch).sendKeys(name);
        waitForPageLoading();
    }

    public void switchCompany(String name){
        if (!name.equals(getCurrentCompany())) {
            clickSwitchCompany();
            searchCompany(name);
            WebElement accName = driver.findElement(By.xpath("//span[normalize-space() = '" + name + "']"));
            //waiter.until(ExpectedConditions.elementToBeClickable(accName));
            accName.click();
            waitForRefreshPage();
        }
    }

    public void clickAllApps(){
        driver.findElement(menuAllApp).click();
        waitForPageLoading();
    }
    public void clickMyJobs(){
        clickAllApps();
        driver.findElement(itemSelected(myJobIndex)).click();
    }

    public void clickSetting(){
        clickAllApps();
        driver.findElement(itemSelected(settingIndex)).click();
    }

}
