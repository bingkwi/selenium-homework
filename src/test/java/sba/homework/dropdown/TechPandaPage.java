package sba.homework.dropdown;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class TechPandaPage {
    private WebDriver driver;
    private final By myAccLink = By.xpath("//div[@class='footer']//a[@title='My Account'][normalize-space()='My Account']");
    private final By createAccBtn = By.xpath("//a[@title='Create an Account']");
    private final By firstNameText = By.xpath("//input[@name='firstname']");
    private final By middleNameText = By.xpath("//input[@name='middlename']");
    private final By lastNameText = By.xpath("//input[@name='lastname']");
    private final By emailText = By.xpath("//input[@name='email']");
    private final By passwordText = By.xpath("//input[@name='password']");
    private final By confirmPasswordText = By.xpath("//input[@name='confirmation']");

    private final By registerBtn = By.xpath("//button[@title='Register']");
    private final By accountLabel = By.xpath("//span[@class='label'][normalize-space()='Account']");
    private final By logoutBtn = By.xpath("//a[@title='Log Out']");

    private final By registerMsg = By.xpath("//span[text()='Thank you for registering with Main Website Store.']");


    public TechPandaPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }


    public void clickMyAccLink() {
        WebElement myAccElement = driver.findElement(myAccLink);
        myAccElement.click();
    }
    public void clickRegisterBtn() {
        WebElement registerBtnElement = driver.findElement(registerBtn);
        registerBtnElement.click();
    }

    public void clickCreateAccBtn() {
        WebElement createBtnElement = driver.findElement(createAccBtn);
        createBtnElement.click();
    }

    public void inputFirstName(String firstName) {
        WebElement firstNameElement = driver.findElement(firstNameText);
        firstNameElement.sendKeys(firstName);
    }

    public void inputMiddleName(String middleName) {
        WebElement middileNameElement = driver.findElement(middleNameText);
        middileNameElement.sendKeys(middleName);
    }

    public void inputLastName(String lastName) {
        WebElement lastNameElement = driver.findElement(lastNameText);
        lastNameElement.sendKeys(lastName);
    }

    public void inputEmailAddress(String email) {
        WebElement emailElement = driver.findElement(emailText);
        email +=  generateRandomNumber() + "@mail.net";
        emailElement.sendKeys(email);
    }

    public void inputPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordText);
        passwordElement.sendKeys(password);
    }

    public void inputConfirmPassword(String confirmPassword) {
        WebElement confirmPasswordElement = driver.findElement(confirmPasswordText);
        confirmPasswordElement.sendKeys(confirmPassword);
    }

    public String showRegisterSuccessfullyMsg() {
        WebElement registerMsgElement = driver.findElement(registerMsg);
        return registerMsgElement.getText();
    }

    public void clickLogout() {
        WebElement accountLabelElement =  driver.findElement(accountLabel);
        accountLabelElement.click();
        WebElement logoutElement = driver.findElement(logoutBtn);
        logoutElement.click();
    }

}
