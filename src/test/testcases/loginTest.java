package test.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.base.setUpBase;
import test.pages.loginPage;

import java.time.Duration;

public class loginTest extends setUpBase {
    private WebDriver driver;
    public loginPage loginPage;

    @BeforeClass
    public void setUp(){
        driver = getDriver();
    }

    @Test
    public void login(){
        loginPage = new loginPage(driver);
        loginPage.login("hong.le+readonly@paradox.ai", "1246");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='breadcrumb_lbl_current']")));
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle,"All Candidates | Candidate Experience Manager");
    }

}
