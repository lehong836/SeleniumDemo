package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class companySitePage extends common {
    private WebDriver driver;

    public companySitePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private By inputSearch = By.xpath("//*[@data-testid= 'widget_input_text']");

    public void inputKeyword(String keyword){
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@data-testid='messenger_lbl_ours']")));
        WebElement inputText = driver.findElement(inputSearch);
        inputText.sendKeys(keyword);
        inputText.sendKeys(Keys.ENTER);
    }

    public String getSearchResult(){
        WebElement searchResult = driver.findElement(By.xpath("//*[@data-testid='message_lbl_job_results'][last()]"));
        WebElement countResult = driver.findElement(By.xpath("(//*[@data-testid='message_lbl_job_results'][last()]//div[@class ='el-card__header']//span)[1]"));
        if (searchResult != null){
            return countResult.getText();
        }
       return "";
    }

}
