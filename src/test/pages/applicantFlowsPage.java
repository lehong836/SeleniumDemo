package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;

public class applicantFlowsPage extends loaderPage{
    private WebDriver driver;

    public applicantFlowsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public void searchAF(String name){
        waitForElementNotVisible(10, xpathLoading);
        WebElement txtSearch = driver.findElement(By.xpath("//div[@data-testid='af_list_search_btn']/input"));
        txtSearch.click();
        txtSearch.sendKeys(name);
        txtSearch.sendKeys(Keys.ENTER);
    }

    public String getAFLabel(){
        waitForElementNotVisible(5, xpathLoading);
        return driver.findElement(By.xpath("(//div[@class = 'text-overflow'])[1]/span")).getText().trim();
    }

    public void clickAF(String name){
        driver.findElement(By.xpath("//span[normalize-space() = '" + name + "']")).click();
    }

    public void clickBtnTargetedJobOnDetail(){
        waitForElementNotVisible(5, xpathLoading);
        driver.findElement(By.xpath("//button[@data-testid='af_details_targeting_show_requisitions_btn']")).click();
    }

    public void searchTargetedJob(String reqID){
        waitForElementNotVisible(5, xpathLoading);
        WebElement txtSearch = driver.findElement(By.xpath("//div[@data-testid='af_targeted_requisitions_modal_search_btn']/input"));
        txtSearch.click();
        txtSearch.sendKeys(reqID);
        txtSearch.sendKeys(Keys.ENTER);
    }

    public Integer countTargetedJobs(){
        waitForElementNotVisible(5, xpathLoading);
        return driver.findElements(By.xpath("//tr[@class = 'el-table__row']")).size();
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
}
