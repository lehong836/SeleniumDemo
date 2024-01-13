package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loaderPage {
    private WebDriver driver;

    protected String xpathLoading = "(//div[@data-testid='loading_wrapper'])[1]";
    public loaderPage(WebDriver driver) {
        this.driver = driver;
    }

    public String waitForElementNotVisible(int timeOutInSeconds, String elementXPath) {
        if ((driver == null) || (elementXPath == null) || elementXPath.isEmpty()) {

            return "Wrong usage of WaitforElementNotVisible()";
        }
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOutInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By
                    .xpath(elementXPath)));
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By
                    .xpath(elementXPath)));
            return null;
        } catch (TimeoutException e) {
            return "Build your own errormessage...";
        }
    }
}
