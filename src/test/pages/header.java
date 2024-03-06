package test.pages;

import main.constant.LoadingType;
import main.helpers.LoadingHelpers;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class header extends common {
    private WebDriver driver;

    private LoadingHelpers loading;
    private By currentAccount = By.xpath("//div[@data-testid = 'header_lbl_company_name']//span");

    private By txtSearch = By.xpath("//div[@class = 'el-input el-input--prefix']//input");

    private Integer myJobIndex = 2;
    private Integer settingIndex = 31;



    public header(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.waiter = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.loading = new LoadingHelpers(driver);
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
        loading.getWaitLoading(LoadingType.DROPDOWN, false);
    }
    public String getCurrentCompany() {
        return driver.findElement(currentAccount).getText();
    }

    public void searchCompany(String name){
        driver.findElement(txtSearch).sendKeys(name);
        loading.getWaitLoading(LoadingType.DROPDOWN, true);
    }

    public void switchCompany(String name){
        if (!name.equals(getCurrentCompany())) {
            clickSwitchCompany();
            searchCompany(name);
            WebElement accName = driver.findElement(By.xpath("//span[normalize-space() = '" + name + "']"));
            accName.click();
         //   loading.getWaitLoading(LoadingType.FULL_SCREEN, true);
            loading.getWaitLoading(LoadingType.DEFAULT, false);
        }
    }

    public void clickAllApps(){
        driver.findElement(menuAllApp).click();
//        loading.getWaitLoading(LoadingType.DEFAULT, true);
    }
    public void clickMyJobs(){
        clickAllApps();
        driver.findElement(itemSelected(myJobIndex)).click();
    }

    public void clickSetting(){
        clickAllApps();
        driver.findElement(itemSelected(settingIndex)).click();
        loading.getWaitLoading(LoadingType.DEFAULT, false);
    }

}
