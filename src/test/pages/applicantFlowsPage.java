package test.pages;

import main.constant.LoadingType;
import main.helpers.LoadingHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.HashMap;

public class applicantFlowsPage extends common {
    private WebDriver driver;

    private LoadingHelpers loading;
    private By txtSearch = By.xpath("//div[@data-testid='af_list_search_btn']/input");
    private By afName = By.xpath("(//div[@class = 'text-overflow'])[1]/span");
    private By btnViewTargetedJob = By.xpath("//button[@data-testid='af_details_targeting_show_requisitions_btn']");
    private By txtSearchTargetedJob = By.xpath("//div[@data-testid='af_targeted_requisitions_modal_search_btn']/input");
    private By emptyLable = By.xpath("//div[@data-testid='empty_lbl_title']");
    private String txtNoJob = "No requisitions found.";
    private By numberOfTargetedJob = By.xpath("//tr[@class = 'el-table__row']");

    public applicantFlowsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        loading = new LoadingHelpers(driver);
    }

    public void searchAF(String name){
        WebElement txtSearch = driver.findElement(this.txtSearch);
        txtSearch.sendKeys(name);
        txtSearch.sendKeys(Keys.ENTER);
        loading.getWaitLoading(LoadingType.DEFAULT, true);
    }

    public String getAFLabel(){
        return driver.findElement(afName).getText().trim();
    }

    public void clickAF(String name){
        driver.findElement(By.xpath("//span[normalize-space() = '" + name + "']")).click();
        loading.getWaitLoading(LoadingType.DEFAULT, true);
    }

    public void clickBtnTargetedJobOnDetail(){
        waiter.until(ExpectedConditions.elementToBeClickable(btnViewTargetedJob));
        driver.findElement(btnViewTargetedJob).click();
        loading.getWaitLoading(LoadingType.DIALOG, false);
    }

    public void searchTargetedJob(String reqID){
        WebElement txtSearch = driver.findElement(txtSearchTargetedJob);
        txtSearch.sendKeys(reqID);
        txtSearch.sendKeys(Keys.ENTER);
        loading.getWaitLoading(LoadingType.DIALOG, false);
    }

    public Integer countTargetedJobs(){
        return driver.findElements(numberOfTargetedJob).size();
    }

    public HashMap<String, String> getTargetedJobInfo(){
        HashMap<String, String> jobInfo = new HashMap<>();
        for (int i = 0; i < this.countTargetedJobs(); i++) {
            String reqID = driver.findElement(By.xpath("//div[@data-testid='af_requisitions_modal_column_job_req_id_row_" + i + "']")).getText();
            String jobTitle = driver.findElement(By.xpath("//div[@data-testid='af_requisitions_modal_column_job_req_title_row_" + i + "']")).getText().trim();
            jobInfo.put("Req ID", reqID);
            jobInfo.put("Job title", jobTitle);
        }
        return jobInfo;
    }

    public Boolean verifySearchNoJob(){
        return driver.findElement(emptyLable).getText().trim().equals(txtNoJob);
    }
}
