package test.testcases;

import main.helpers.ExcelHelpers;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.base.setUpBase;
import test.pages.applicantFlowsPage;
import test.pages.header;
import test.pages.myJobsPage;
import test.pages.settingPage;
import test.datatest.common;
import test.datatest.applicantFlows;

import java.util.HashMap;

public class applicantFlowsTest extends setUpBase {
    private WebDriver driver;

    private header navigator;

    private settingPage setting;

    private applicantFlowsPage afPage;

    private myJobsPage myJob;

    private ExcelHelpers excel;


    @BeforeClass
    public void setUp() {
        driver = getDriver();
        excel = new ExcelHelpers();
        init();
    }

    public void init(){
        navigator = new header(driver);
        setting = new settingPage(driver);
        afPage = new applicantFlowsPage(driver);
        myJob = new myJobsPage(driver);
        navigator.switchCompany(common.companyName);
    }

    @Test
    public void verifyTargetedJobOnAF() throws Exception {
        //file input
        excel.setExcelFile("src/test/resources/testdata.xlsx","applicant_flow");

        //total rows data in file input
        int totalOfRows = 2;

        // read data from file input
        for (int i = 1; i < totalOfRows; i++) {
            // change status on My Jobs
            navigator.clickMyJobs();
            myJob.waitToggleClickable();
            Boolean jobStatus = myJob.changeJobStatus(excel.getCellDataByColumName("reqID", i));

            HashMap<String, String> expectedJobInfo = new HashMap<>();
            expectedJobInfo.put("Req ID", excel.getCellDataByColumName("reqID", i));
            expectedJobInfo.put("Job title", excel.getCellDataByColumName("jobTitle", i));

            // go to AF menu
            navigator.clickSetting();
            setting.clickApplicantFlows();

            // choose AF
            afPage.searchAF(excel.getCellDataByColumName("afName", i));
            Assert.assertEquals(afPage.getAFLabel(), excel.getCellDataByColumName("afName", i));
            afPage.clickAF(excel.getCellDataByColumName("afName", i));

            // verify targeted jobs
            afPage.clickBtnTargetedJobOnDetail();
            afPage.searchTargetedJob(excel.getCellDataByColumName("reqID", i));

            if (jobStatus) {
                Assert.assertEquals(afPage.countTargetedJobs(), Integer.parseInt(excel.getCellDataByColumName("numberOfTargetedJob", i)));
                Assert.assertTrue(afPage.getTargetedJobInfo().equals(expectedJobInfo));
            } else {
                Assert.assertTrue(afPage.verifySearchNoJob());
            }
        }
    }
}
