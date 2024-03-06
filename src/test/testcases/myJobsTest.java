package test.testcases;

import main.helpers.ExcelHelpers;
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

    private ExcelHelpers excel;


    @BeforeClass
    public void setUp() {
        driver = getDriver();
        excel = new ExcelHelpers();
        myJobPage = new myJobsPage(driver);
        switchAccount();
    }

    public void switchAccount(){
        navigator = new header(driver);
        navigator.switchCompany(common.companyName);
        navigator.clickMyJobs();
        myJobPage.waitToggleClickable();
    }

   // @Test
//    public void changeJobStatus(){
//        myJobPage.inputSearchKeyword(myJobs.reqID);
//        myJobPage.waitForSearching();
//        for (int i = 0; i <= 1; i++) {
//            Boolean preStatus = myJobPage.getCurrentStatus();
//            myJobPage.clickBtnChangeStatus();
//            Assert.assertTrue(myJobPage.verifyMyJobModal(myJobs.jobTitle, preStatus));
//            myJobPage.clickConfirmChangeStatus(preStatus);
//            Boolean newStatus = myJobPage.getCurrentStatus();
//            Assert.assertTrue(preStatus != newStatus);
//        }
//    }
        @Test
        public void changeJobStatus() throws Exception {
            //set file input
            excel.setExcelFile("src/test/resources/testdata.xlsx","my_job");
            for (int i=1; i < 3; i++){
                myJobPage.inputSearchKeyword(excel.getCellDataByColumName("reqID", i));
                myJobPage.waitForSearching();
                Boolean preStatus = myJobPage.getCurrentStatus();
                myJobPage.clickBtnChangeStatus();
                Assert.assertTrue(myJobPage.verifyMyJobModal(excel.getCellDataByColumName("jobTitle", i), preStatus));
                myJobPage.clickConfirmChangeStatus(preStatus);
                Boolean newStatus = myJobPage.getCurrentStatus();
                Assert.assertTrue(preStatus != newStatus);
            }


        }
}
