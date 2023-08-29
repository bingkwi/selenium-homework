package sba.homework.customdropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import sba.common.Initializer;

import java.time.Duration;
import java.util.List;

public class CustomDropDownTest {
    WebDriver driver;
    WebDriverWait explicitWait;

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

    public void selectItemInCustomDropDown(String parentXpath, String childXpath, String expectedItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();
        Thread.sleep(2000);

        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        List<WebElement> items = driver.findElements(By.xpath(childXpath));
        for (WebElement tempElement : items) {
            if (tempElement.getText().equals(expectedItem)) {
                tempElement.click();
                Thread.sleep(2000);
                break;
            }
        }
    }

    @Test
    public void jQuery() throws InterruptedException {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        String parentXpath = "//span[@id='number-button']";
        String childXpath = "//ul[@id='number-menu']/li/div";
        String expectedItem = "7";
        //
        selectItemInCustomDropDown(parentXpath, childXpath, expectedItem);

    }

    @Test
    public void reactJS() throws InterruptedException {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        String parentXpath = "//i[@class='dropdown icon']";
        String childXpath = "//div[contains(@class,'item')]/span[@class='text']";
        String currentSelection = "//div[@class='divider text']";
        String expectedItem1 = "Aland Islands";
        String expectedItem2 = "Benin";
        selectItemInCustomDropDown(parentXpath, childXpath, expectedItem1);
        Assert.assertEquals(driver.findElement(By.xpath(currentSelection)).getText(), expectedItem1);
        selectItemInCustomDropDown(parentXpath, childXpath, expectedItem2);
        Assert.assertEquals(driver.findElement(By.xpath(currentSelection)).getText(), expectedItem2);
    }

}
