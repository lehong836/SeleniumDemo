package main.helpers;

import main.constant.LoadingType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoadingHelpers {

    private WebDriver driver;
    private WebDriverWait waiter;

    protected String defaultLoad = "//div[@data-testid = 'loading_wrapper']";
    protected String loadDialog = "//div[@class='el-dialog']//div[@data-testid = 'loading_spinner']";

    protected String loadDropdown = "//ul[contains(@id, 'dropdown-menu')]//div[@data-testid = 'loading_wrapper']";

    protected  String loadPage_1 = "//div[contains(@class, 'is-fullscreen')]//div[@data-testid = 'loading_wrapper']";

    protected String loadPage_2 = "//div[@data-testid = 'loading_wrapper' and not(contains(@style, 'display'))]";

    protected String loadAFBuilder = "//section[@data-testid='af_builder']//div[@data-testid = 'loading_wrapper']";

    protected Boolean isVisible;

    public LoadingHelpers(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    public void waiting(String xpath){
        if (!this.isVisible) {
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        }
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
    }

    public void getWaitLoading(int type, Boolean isVisible){
        this.isVisible = isVisible;
        switch (type){
            case LoadingType.DROPDOWN -> this.waiting(loadDropdown);
            case LoadingType.NO_STYLE_DISPLAY -> this.waiting(loadPage_2);
            case LoadingType.AF_BUILDER -> this.waiting(loadAFBuilder);
            case LoadingType.DIALOG -> this.waiting(loadDialog);
            case LoadingType.FULL_SCREEN -> this.waiting(loadPage_1);
            default -> this.waiting(defaultLoad);
        }

    }
}
