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
    protected String xpathLoading = "(//div[@data-testid='loading_wrapper'])[1]";
    private final WebDriver driver;


    public common(WebDriver driver) {
        this.driver = driver;
        waiter = new WebDriverWait(this.driver, Duration.ofSeconds(5));
    }

    public String waitForElementNotVisible(String elementXPath) {
        if ((driver == null) || (elementXPath == null) || elementXPath.isEmpty()) {

            return "Wrong usage of WaitforElementNotVisible()";
        }
        try {
            WebElement el = driver.findElement(By.xpath(elementXPath));
            if (el.isDisplayed()) {
                waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(elementXPath)));
            }
            return null;
        } catch (TimeoutException e) {
            return "Build your own errormessage...";
        }
    }

    public void waitForPageLoading() {
        String xpathLoading = "//div[@data-testid='loading_wrapper']";
//        Boolean isLoading = driver.findElements(By.xpath(xpathLoading)).stream().anyMatch(WebElement::isDisplayed);
//        if (!isLoading) {
//            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLoading)));
//        }
        waiter.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath(xpathLoading))));
    }

    public By getSpecXpath() {
        return By.xpath("");
    }

    public void waitForRefreshPage() {
        WebElement el = driver.findElement(By.xpath(xpathLoading));
        waiter.until(ExpectedConditions.invisibilityOf(el));
        if (!driver.findElement(By.xpath(xpathLoading)).isDisplayed()) {
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathLoading)));
        }
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpathLoading)));
    }

}
