package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;

public class myJobsPage extends common {
    private WebDriver driver;

    private header navigator;

    public myJobsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.navigator = new header(driver);
    }

    private By txtSearch = By.xpath("//div[@data-testid = 'my_jobs_input_search']//input");

    private By btnStatus = By.xpath("//label[@data-testid='my_job_item_toggle_switch']");

    private By txtStatus = By.xpath("//label[@data-testid='my_job_item_toggle_switch']//span");

    private By btnPostJob = By.xpath("(//div[@data-testid = 'my-job-modal']//span[normalize-space() = 'Post Job'])[last()]");

    private By btnCloseJob = By.xpath("//div[@class = 'el-message-box__btns']//span[normalize-space() = 'Close Job']");

    private By popupCloseJob = By.xpath("(//div[@class = 'el-message-box__header']//ancestor::div)[2]");

    private By CloseJobBtnCancel = By.xpath("//div[@class = 'el-message-box__btns']//span[normalize-space() = 'Cancel']");

    private By CloseJobContent = By.xpath("//div[@class = 'el-message-box__message']");

    private By CloseJobTitle = By.xpath("//div[@class = 'el-message-box__title']//span");

    private By popupPostJob = By.xpath("//div[@data-testid = 'my-job-modal']/div");

    private By postJobBtnCancel = By.xpath("(//div[@data-testid = 'my-job-modal']//span[normalize-space() = 'Cancel'])[last()]");

    private By postJobContent = By.xpath("//div[@data-testid = 'my-job-modal']//div[@class = 'el-dialog__body']");

    private By postJobTitle = By.xpath("//div[@data-testid = 'my-job-modal']//div[@class = 'el-dialog__header']//span");

    private By toast = By.xpath("//div[contains(@class,'toasted')]");

    private By btnFilter = By.xpath("//div[@data-testid='my_jobs_filter_dropdown']");



    public void inputSearchKeyword(String keyword){
        WebElement textboxSearch = driver.findElement(txtSearch);
        textboxSearch.sendKeys(keyword);
        textboxSearch.sendKeys(Keys.ENTER);
        waitForPageLoading();
    }

    public Boolean getCurrentStatus(){
        WebElement status = driver.findElement(txtStatus);
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
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupCloseJob));
            btnCancel = driver.findElement(CloseJobBtnCancel);
            content = driver.findElement(CloseJobContent);
            btnConfirm = driver.findElement(btnCloseJob);
            jobTitle = driver.findElement(CloseJobTitle);
        } else {
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupPostJob));
            btnCancel = driver.findElement(postJobBtnCancel);
            btnConfirm = driver.findElement(btnPostJob);
            content = driver.findElement(postJobContent);
            jobTitle = driver.findElement(postJobTitle);
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
        driver.findElement(btnStatus).click();
    }

    public void clickConfirmChangeStatus(boolean isOpen){
        if (isOpen){
            driver.findElement(btnCloseJob).click();
        } else {
            driver.findElement(btnPostJob).click();
        }
        waiter.until(ExpectedConditions.visibilityOfElementLocated(toast));
        waiter.until(ExpectedConditions.invisibilityOfElementLocated(toast));
    }



    public Boolean changeJobStatus(String reqID){
        navigator.clickMyJobs();
        this.inputSearchKeyword(reqID);
        Boolean curStatus = this.getCurrentStatus();
        this.clickBtnChangeStatus();
        this.clickConfirmChangeStatus(curStatus);
        return !curStatus;
    }

    public void waitToggleClickable(){
        waiter.until(ExpectedConditions.visibilityOfElementLocated(btnStatus));
        waitForPageLoading();
    }

    public void waitForSearching() {
        String Item = "//div[contains(@data-testid,'my_job_item')]";
        waiter.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.xpath(Item))));
        waiter.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Item)));
    }



}
