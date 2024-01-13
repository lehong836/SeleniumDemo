package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class myJobsPage extends loaderPage {
    private WebDriver driver;

    private header navigator;

    public myJobsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.navigator = new header(driver);
    }

    private By txtSearch = By.xpath("//div[@data-testid = 'my_jobs_input_search']//input");

    private By btnStatus = By.xpath("//label[@data-testid='my_job_item_toggle_switch']");

    private By btnPostJob = By.xpath("(//div[@data-testid = 'my-job-modal']//span[normalize-space() = 'Post Job'])[last()]");

    private By btnCloseJob = By.xpath("//div[@class = 'el-message-box__btns']//span[normalize-space() = 'Close Job']");

    public void inputSearchKeyword(String keyword){
        waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]");
        WebElement textboxSearch = driver.findElement(txtSearch);
        textboxSearch.sendKeys(keyword);
        textboxSearch.sendKeys(Keys.ENTER);
    }

    public Boolean getCurrentStatus(){
        waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]");
        WebElement status = driver.findElement(By.xpath("//label[@data-testid='my_job_item_toggle_switch']//span"));
        String currentStatus = status.getAttribute("class");
        if (currentStatus.contains("icon-check2")) {
            return true;
        }
        return false;
    }

    public HashMap<String, String> getDialogContent(boolean isOpen){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement btnCancel;
        WebElement btnConfirm;
        WebElement content;
        WebElement jobTitle;
        if (isOpen) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class = 'el-message-box__header']//ancestor::div)[2]")));
            btnCancel = driver.findElement(By.xpath("//div[@class = 'el-message-box__btns']//span[normalize-space() = 'Cancel']"));
            content = driver.findElement(By.xpath("//div[@class = 'el-message-box__message']"));
            btnConfirm = driver.findElement(btnCloseJob);
            jobTitle = driver.findElement(By.xpath("//div[@class = 'el-message-box__title']//span[normalize-space() = 'Store Leader Trainee 06 update']"));
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-testid = 'my-job-modal']/div")));
            btnCancel = driver.findElement(By.xpath("(//div[@data-testid = 'my-job-modal']//span[normalize-space() = 'Cancel'])[last()]"));
            btnConfirm = driver.findElement(btnPostJob);
            content = driver.findElement(By.xpath("//div[@data-testid = 'my-job-modal']//div[@class = 'el-dialog__body']"));
            jobTitle = driver.findElement(By.xpath("//div[@data-testid = 'my-job-modal']//div[@class = 'el-dialog__header']//span"));
        }
        HashMap<String, String> myJobModal = new HashMap<String, String>();
        myJobModal.put("title",jobTitle.getText());
        myJobModal.put("content", content.getText().trim());
        myJobModal.put("btnCancel", Boolean.toString(btnCancel.isDisplayed()));
        myJobModal.put("btnConfirm", Boolean.toString(btnConfirm.isDisplayed()));
        return myJobModal;
    }

    public Boolean verifyMyJobModal(String title, boolean isOpen){
        HashMap<String, String> expected = new HashMap<String, String>();
        expected.put("title", title);
        if (isOpen){
            expected.put("content", "Are you sure you want to close this job?");
        } else {
            expected.put("content", "Are you sure you want to post this job?");
        }
        expected.put("btnCancel", "true");
        expected.put("btnConfirm", "true");
        HashMap<String, String> myJobModal = this.getDialogContent(isOpen);
        return myJobModal.equals(expected);
    }

    public void clickBtnChangeStatus(){
        waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]");
        WebElement buttonStatus = driver.findElement(btnStatus);
        buttonStatus.click();
    }

    public void clickConfirmChangeStatus(boolean isOpen){
        if (isOpen){
            driver.findElement(btnCloseJob).click();
        } else {
            driver.findElement(btnPostJob).click();
        }
        waitForElementNotVisible(5,"(//div[@data-testid='loading_wrapper'])[1]");
    }

    public Boolean changeJobStatus(String reqID){
        navigator.clickMyJobs();
        this.inputSearchKeyword(reqID);
        Boolean curStatus = this.getCurrentStatus();
        this.clickBtnChangeStatus();
        this.clickConfirmChangeStatus(curStatus);
        return !curStatus;
    }


}
