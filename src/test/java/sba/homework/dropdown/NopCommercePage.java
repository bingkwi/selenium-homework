package sba.homework.dropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class NopCommercePage {
    private WebDriver driver;
    private final By registerBtnHeader = By.cssSelector("a.ico-register");
    private final By firstNameText = By.id("FirstName");
    private final By lastNameText = By.id("LastName");
    private final By dayInput = By.name("DateOfBirthDay");
    private final By monthInput = By.name("DateOfBirthMonth");
    private final By yearInput = By.name("DateOfBirthYear");
    private final By emailText = By.id("Email");
    private final By passwordText = By.id("Password");
    private final By confirmPasswordText = By.id("ConfirmPassword");
    private final By registerBtn = By.id("register-button");
    private final By loginBtnHeader = By.cssSelector("a.ico-login");
    private final By loginBtn = By.xpath("//button[normalize-space()='Log in']");
    private final By myAccountBtn = By.xpath("//a[@class='ico-account']");

    public NopCommercePage(WebDriver driver) {
        this.driver = driver;
    }

    public void registerNewAccount(String firstName, String lastName, String email, String password, String day, String month,String year) {
        driver.findElement(registerBtnHeader).click();
        driver.findElement(firstNameText).sendKeys(firstName);
        driver.findElement(lastNameText).sendKeys(lastName);

        Select daySelection = new Select(driver.findElement(dayInput));
        daySelection.selectByVisibleText(day);
        //Assert.assertEquals(daySelection.getOptions().size(), 32);

        Select monthSelection = new Select(driver.findElement(monthInput));
        monthSelection.selectByVisibleText(month);
        //Assert.assertEquals(monthSelection.getOptions().size(), 13);

        Select yearSelection = new Select(driver.findElement(yearInput));
        yearSelection.selectByVisibleText(year);
        //Assert.assertEquals(yearSelection.getOptions().size(), 112);

        driver.findElement(emailText).sendKeys(email);
        driver.findElement(passwordText).sendKeys(password);
        driver.findElement(confirmPasswordText).sendKeys(password);
        driver.findElement(registerBtn).click();
    }
     public void login(String email, String password) {
        driver.findElement(loginBtnHeader).click();
        driver.findElement(emailText).sendKeys(email);
        driver.findElement(passwordText).sendKeys(password);
        driver.findElement(loginBtn).click();
        driver.findElement(myAccountBtn).click();
    }

}
