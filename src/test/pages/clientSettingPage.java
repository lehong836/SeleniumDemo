package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class clientSettingPage {
    private WebDriver driver;

    private By searchCompany = By.xpath("//*[@id = 'job_search_id']");

    private By inputSearch = By.xpath("//*[@id ='job_search_id']//*[@data-testid = 'multidropdown_input_search']");


    public By getMenuList(String menuName) {
        return By.xpath("//aside[contains(@class, 'el-aside')]//div[@class = 'el-scrollbar__view']//span[normalize-space()= '" + menuName + "']");
    }


}
