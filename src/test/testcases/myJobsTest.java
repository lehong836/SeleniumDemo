package test.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.base.setUpBase;
import test.pages.myJobsPage;
import test.pages.header;
import test.datatest.common;
import test.datatest.myJobs;

public class myJobsTest extends setUpBase {

    private WebDriver driver;

    private header navigator;

    private myJobsPage myJobPage;


    @BeforeClass
    public void setUp() {
        driver = getDriver();
        myJobPage = new myJobsPage(driver);
        switchAccount();
    }

    public void switchAccount(){
        navigator = new header(driver);
        navigator.switchCompany(common.companyName);
        navigator.clickMyJobs();
        myJobPage.waitToggleClickable();
    }

    @Test
    public void changeJobStatus(){
        myJobPage.inputSearchKeyword(myJobs.reqID);
        myJobPage.waitForSearching();
        for (int i = 0; i <= 1; i++) {
            Boolean preStatus = myJobPage.getCurrentStatus();
            myJobPage.clickBtnChangeStatus();
            Assert.assertTrue(myJobPage.verifyMyJobModal(myJobs.jobTitle, preStatus));
            myJobPage.clickConfirmChangeStatus(preStatus);
            Boolean newStatus = myJobPage.getCurrentStatus();
            Assert.assertTrue(preStatus != newStatus);
        }
    }


}
