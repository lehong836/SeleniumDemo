package test.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.base.setUpBase;
import test.pages.companySitePage;

public class companySiteTest extends setUpBase {
    private WebDriver driver;
    private companySitePage companySite;

    @BeforeClass
    public void setUp(){
        driver = getDriver();
    }

    @BeforeClass
    public void initializeTestBaseSetup() {
        String appURL = "https://olivia.paradox.ai/co/CloneParadoxVNTestclonecompany";
        try {
            // Khởi tạo driver và browser
            setDriver(appURL);
        } catch (Exception e) {
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @Test
    public void searchJob(){
        companySite = new companySitePage(driver);
        companySite.inputKeyword("any jobs anywhere");
        String actualResult = companySite.getSearchResult().trim();
        Assert.assertEquals(actualResult, "20 Recommended Jobs");
    }


}
