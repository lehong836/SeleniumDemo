package test.pages;

import main.constant.LoadingType;
import main.helpers.LoadingHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class settingPage extends common {
    private WebDriver driver;

    private LoadingHelpers loading;

    public settingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        loading = new LoadingHelpers(driver);
    }

    public By xpathItem(String name){
        return By.xpath("//span[normalize-space() = '" + name + "']");
    }

    public void clickApplicantFlows(){
       driver.findElement(xpathItem("Applicant Flows")).click();
        loading.getWaitLoading(LoadingType.DEFAULT, true);
    }
}
