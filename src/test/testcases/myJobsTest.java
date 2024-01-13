package test.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import test.base.setUpBase;
import test.pages.myJobsPage;
import test.pages.header;

public class myJobsTest extends setUpBase {

    private WebDriver driver;

    private header navigator;

    private myJobsPage myJobs;


    @BeforeClass
    public void setUp() {
        driver = getDriver();
        switchAccount();
    }

    public void switchAccount(){
        navigator = new header(driver);
        navigator.switchCompany("ParadoxVN - Hong Test: AF");
        navigator.clickMyJobs();
    }

    @Test
    public void changeJobStatus(){
        myJobs = new myJobsPage(driver);
        myJobs.inputSearchKeyword("SC004");
        for (int i = 0; i <= 1; i++) {
            Boolean preStatus = myJobs.getCurrentStatus();
            myJobs.clickBtnChangeStatus();
            Assert.assertTrue(myJobs.verifyMyJobModal("Store Leader Trainee 06 update", preStatus));
            myJobs.clickConfirmChangeStatus(preStatus);
            Boolean newStatus = myJobs.getCurrentStatus();
            Assert.assertTrue(preStatus != newStatus);
        }
    }


}
