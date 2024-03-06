package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicReference;

public class common {
    protected WebDriverWait waiter;

    protected  String loadPage = "//div[contains(@class, 'is-fullscreen')]//div[@data-testid = 'loading_spinner']";

    private final WebDriver driver;


    public common(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }


    public void waitForPageLoading() {
        waiter.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath(loadPage))));
    }

}
