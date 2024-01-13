package test.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.base.setUpBase;
import test.pages.applicantFlowsPage;
import test.pages.header;
import test.pages.myJobsPage;
import test.pages.settingPage;

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
        navigator.switchCompany("ParadoxVN - Hong Test: AF");
    }

    @Test

    public void verifyTargetedJobOnAF(){
        String expectedAF = "Default Applicant Flow";
        String expectedJob = "SC004";

        // change status on My Jobs
        navigator.clickMyJobs();
        Boolean jobStatus = myJob.changeJobStatus(expectedJob);

        HashMap<String, String> expectedJobInfo = new HashMap<>();
        expectedJobInfo.put("Req ID", expectedJob);
        expectedJobInfo.put("Job title", "Store Leader Trainee 06 update");

        // go to AF menu
        navigator.clickSetting();
        setting.clickApplicantFlows();

        // choose AF
        afPage.searchAF(expectedAF);
        Assert.assertEquals(afPage.getAFLabel(), expectedAF);
        afPage.clickAF(expectedAF);

        // verify targeted jobs
        afPage.clickBtnTargetedJobOnDetail();
        afPage.searchTargetedJob(expectedJob);
        if (jobStatus) {
            Assert.assertEquals(afPage.countTargetedJobs(), 2);
            Assert.assertTrue(afPage.getTargetedJobInfo().equals(expectedJobInfo));
        }else {
            Assert.assertEquals(driver.findElement(By.xpath("//div[@data-testid='empty_lbl_title']")).getText().trim(), "No requisitions found.");
        }
    }
}
