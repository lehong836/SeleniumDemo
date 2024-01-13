package test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class loginPage {
    private WebDriver driver;
    private By inputEmail = By.xpath("//input[@data-testid='login_input_lookup']");
    private By inputPassword = By.xpath("//input[@data-testid='login_input_password']");
    private By nextButton = By.xpath("//button[normalize-space()='Next']");
    private By loginButton = By.xpath("//button[normalize-space()='Sign in']");

    public loginPage(WebDriver driver){
        this.driver = driver;
    }

    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean verifyLoginPageTitle(){
        return getLoginPageTitle().equals("Login");
    }

    public void inputEmail(String email){
        WebElement emailTextbox = driver.findElement(inputEmail);
        emailTextbox.sendKeys(email);

    }

    public void inputPassword(String password){
        WebElement passTextbox = driver.findElement(inputPassword);
        passTextbox.sendKeys(password);
    }

    public void clickNext(){
        WebElement next = driver.findElement(nextButton);
        if (next.isDisplayed()){
            next.submit();
        }
    }

    public void clickLogin(){
        WebElement login = driver.findElement(loginButton);
        if (login.isDisplayed()){
            login.submit();
        }
    }

    public void login(String email, String password){
       inputEmail(email);
       clickNext();
       inputPassword(password);
       clickLogin();
    }
}
