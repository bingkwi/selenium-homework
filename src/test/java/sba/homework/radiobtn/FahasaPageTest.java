package sba.homework.radiobtn;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sba.common.Initializer;

import java.time.Duration;

public class FahasaPageTest {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        Initializer.initialize();
        driver = new ChromeDriver(new ChromeOptions());
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @AfterMethod
    public void afterMethod() {
        driver.close();
    }

    @Test
    public void switchLoginTab() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        By loginBtn = By.xpath("//button[@class='fhs-btn-login']");
        By loginTab = By.xpath("//li[contains(@class,'popup-login')]/a[text()='Đăng nhập']");

        driver.findElement(loginTab).click();
        // verify login btn is disabled
        Assert.assertFalse(driver.findElement(loginBtn).isEnabled());

        driver.findElement(By.id("login_username")).sendKeys("John@gmail.com");
        driver.findElement(By.id("login_password")).sendKeys("111111");
        Thread.sleep(2000);

        //verify login btn is enabled
        Assert.assertTrue(driver.findElement(loginBtn).isEnabled());
        driver.navigate().refresh();
        driver.findElement(loginTab).click();

        //verify background color
        String loginBgColor = driver.findElement(loginBtn).getCssValue("background-color");
    }

    @Test
    public void defaultCheckbox() throws InterruptedException {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By zoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();");
        By luggageCheckbox = By.id("eq3");
        driver.findElement(zoneCheckbox).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(zoneCheckbox).isSelected());

    }
}
