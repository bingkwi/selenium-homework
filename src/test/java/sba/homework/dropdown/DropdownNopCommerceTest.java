package sba.homework.dropdown;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sba.common.Initializer;

public class DropdownNopCommerceTest {
    private WebDriver driver;
    public NopCommercePage page;
    public Select select;
    public String firstName, lastName, email, password, day, month, year;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        Initializer.initialize();
        driver = new ChromeDriver(new ChromeOptions());
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        page = new NopCommercePage(driver);
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

    public int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(9999);
    }

    @Test(enabled = false)
    public void TC_01_SampleCode() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        String[] testing = { "Manual", "Mobile", "Security", "Perfomance" };

        select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
        Assert.assertTrue(select.isMultiple());

        for (String value : testing) {
            select.selectByVisibleText(value);
            Thread.sleep(500);
        }

        List<WebElement> selectedOption = select.getAllSelectedOptions();
        Assert.assertEquals(selectedOption.size(), 4);

        List<String> actualValues = new ArrayList<String>();

        for (WebElement option : selectedOption) {
            actualValues.add(option.getText());
        }

        List<String> expectedValues = Arrays.asList(testing);

        Assert.assertEquals(actualValues, expectedValues);
    }

    @Test
    public void TC_02_registerAndVerifyResults() throws InterruptedException {
        firstName = "Cookies";
        lastName = "Nguyen";
        email = "cookies.nguyen" + getRandomNumber() + "@homie.vn";
        password = "111111";
        day = "10";
        month = "August";
        year = "1999";

        driver.get("https://demo.nopcommerce.com/");
        page.registerNewAccount(firstName, lastName, email, password, day, month, year);
        page.login(email, password);

//        System.out.println(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"));
        Assert.assertEquals(driver.findElement(By.name("FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.name("LastName")).getAttribute("value"), lastName);
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthDay")).getAttribute("value"), day);
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthMonth")).getAttribute("value"), "8");
        Assert.assertEquals(driver.findElement(By.name("DateOfBirthYear")).getAttribute("value"), year);



    }


}