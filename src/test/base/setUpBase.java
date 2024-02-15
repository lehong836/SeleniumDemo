package test.base;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import test.pages.loginPage;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class setUpBase {
    private WebDriver driver;
    private loginPage loginPage;

    public void setDriver(String appURL) {
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", "C:/Webdriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public WebDriver getDriver() {
        return driver;
    }
    @BeforeClass
    public void initializeTestBaseSetup() {
        String appURL = "https://olivia.paradox.ai";
        try {
            // Khởi tạo driver và browser
            setDriver(appURL);
            login();
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }


    public void login(){
        loginPage = new loginPage(driver);
        loginPage.login("hong.le+readonly@paradox.ai", "1246");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='breadcrumb_lbl_current']")));
    }


}
