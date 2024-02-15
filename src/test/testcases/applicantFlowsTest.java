package test.testcases;

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


    @BeforeClass
    public void setUp() {
        driver = getDriver();
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

    public void verifyTargetedJobOnAF(){
        // change status on My Jobs
        navigator.clickMyJobs();
        Boolean jobStatus = myJob.changeJobStatus(applicantFlows.reqID);

        HashMap<String, String> expectedJobInfo = new HashMap<>();
        expectedJobInfo.put("Req ID", applicantFlows.reqID);
        expectedJobInfo.put("Job title", applicantFlows.jobTitle);

        // go to AF menu
        navigator.clickSetting();
        setting.clickApplicantFlows();

        // choose AF
        afPage.searchAF(applicantFlows.defaultAF);
        Assert.assertEquals(afPage.getAFLabel(), applicantFlows.defaultAF);
        afPage.clickAF(applicantFlows.defaultAF);

        // verify targeted jobs
        afPage.clickBtnTargetedJobOnDetail();
        afPage.searchTargetedJob(applicantFlows.reqID);
        if (jobStatus) {
            Assert.assertEquals(afPage.countTargetedJobs(), 2);
            Assert.assertTrue(afPage.getTargetedJobInfo().equals(expectedJobInfo));
        }else {
            Assert.assertTrue(afPage.verifySearchNoJob());
        }
    }
}
