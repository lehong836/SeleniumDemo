package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class settingPage extends loaderPage {
    private WebDriver driver;

    public settingPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public By xpathItem(String name){
        return By.xpath("//span[normalize-space() = '" + name + "']");
    }

    public void clickApplicantFlows(){
       waitForElementNotVisible(5, xpathLoading);
       driver.findElement(xpathItem("Applicant Flows")).click();
    }
}
