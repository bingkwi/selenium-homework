package sba.homework.dropdown;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sba.common.Initializer;

public class TechMainTest {
    private WebDriver driver;
    private TechPandaPage page;
    public String firstName, lastName, email, password, confirmPassword;
    public String homePageUrl = "http://live.techpanda.org/index.php/";

    @BeforeMethod
    public void setUp() throws InterruptedException {
        Initializer.initialize();
        driver = new ChromeDriver();
        page = new TechPandaPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(homePageUrl);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    public void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void TC_01_handleTextbox() {
        firstName = "first name";
        lastName = "last name";
        email = "email";
        password = "111111";
        confirmPassword = "111111";

        page.clickMyAccLink();
        page.clickCreateAccBtn();
        page.inputFirstName(firstName);
        page.inputLastName(lastName);
        page.inputEmailAddress(email);
        page.inputPassword(password);
        page.inputConfirmPassword(confirmPassword);
        //sleepInSecond(3);
        page.clickRegisterBtn();
        //sleepInSecond(3);

        assertEquals(page.showRegisterSuccessfullyMsg(), "Thank you for registering with Main Website Store.");

//		String[] contactInfo = new String[2];
//		String info = driver.findElement(By.xpath("//div[@class='box-content']//p")).toString();
//		WebElement info1 = info.getAttribute("innerHTML");
//		contactInfo=info;
//		System.out.println("name = " + contactInfo[0].toString());
//		System.out.println("email = " + contactInfo[1].toString());

        WebElement contactInfoElement = driver.findElement(By.xpath("(//div[@class='box-content']//following-sibling::p)[1]"));
        //contactInfoElement.getText();
        System.out.println(contactInfoElement.getText());

        sleepInSecond(30);
        page.clickLogout();

        sleepInSecond(300);
        assertEquals(homePageUrl,driver.getCurrentUrl());





    }

}