package sba.homework.customdropdown;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import org.openqa.selenium.remote.CapabilityType;

import java.time.Duration;
import java.util.List;

public class CustomDropDownTest {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor javascriptExecutor;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        Initializer.initialize();
        ChromeOptions capability = new ChromeOptions();
        capability.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS,true);
        driver = new ChromeDriver(capability);
        javascriptExecutor = (JavascriptExecutor) driver;
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

    /**
     *
     * @param parentXpath
     * @param childXpath
     * @param expectedItem
     * @throws InterruptedException
     */
    public void selectItemInCustomDropDownUpdated(String parentXpath, String childXpath, String expectedItem) throws InterruptedException {
        driver.findElement(By.xpath(parentXpath)).click();
        Thread.sleep(2000);

        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

        List<WebElement> items = driver.findElements(By.xpath(childXpath));
        for (WebElement tempElement : items) {
            if (tempElement.getText().equals(expectedItem)) {
                if (tempElement.isDisplayed()) {
                    tempElement.click();
                    Thread.sleep(2000);
                } else {
                    // scroll to element
                    javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", tempElement);
                    // click by Javascript
                    javascriptExecutor.executeScript("arguments[0].click();", tempElement);
                }
                break;
            }
        }
    }

    public void selectItemInEditableDropDown(String parentXpath, String childXpath, String expectedItem) throws InterruptedException {
        // sendKeys
        driver.findElement(By.xpath(parentXpath)).sendKeys(expectedItem);
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
        String parentXpath2 = "//input[@class='search']";
        String childXpath = "//div[contains(@class,'item')]/span[@class='text']";
        String currentSelection = "//div[@class='divider text']";
        String expectedItem1 = "Aland Islands";
        String expectedItem2 = "Benin";
        selectItemInCustomDropDown(parentXpath, childXpath, expectedItem1);
        //Assert.assertEquals(driver.findElement(By.xpath(currentSelection)).getText(), expectedItem1);
        selectItemInCustomDropDown(parentXpath, childXpath, expectedItem2);
        //Assert.assertEquals(driver.findElement(By.xpath(currentSelection)).getText(), expectedItem2);

        /*
        editable dropdown
         */
        selectItemInEditableDropDown(parentXpath2, childXpath, expectedItem1);
        Assert.assertEquals(driver.findElement(By.xpath(currentSelection)).getText(), expectedItem1);
    }

    @Test
    public void tiemChungCovid() throws InterruptedException {
        driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
        String provinceXpath = "//ng-select[@bindvalue='provinceCode']//span[@class='ng-arrow-wrapper']";
        String districtXpath = "//ng-select[@bindvalue='districtCode']//span[@class='ng-arrow-wrapper']";
        String wardXpath = "//ng-select[@bindvalue='wardCode']//span[@class='ng-arrow-wrapper']";
        String childXpath = "//span[contains(@class,'ng-option-label')]";
        String province = "Tỉnh Thái Nguyên";
        String district = "Huyện Đại Từ";
        String ward = "Thị Trấn Hùng Sơn";

        selectItemInCustomDropDown(provinceXpath, childXpath, province);

        String actualProvince = (String) javascriptExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode']\").innerText.replace(\"×\",\" \").trim();");
        Assert.assertEquals(actualProvince, province);

        // or using function in code
        String expectedValue = (String) javascriptExecutor.executeScript("return document.querySelector(\"ng-select[bindvalue='provinceCode']\").innerText");
        //Assert.assertEquals(expectedValue.replace("×", " ").trim(), province);

        // or getText()
        expectedValue = driver.findElement(By.xpath("//ng-select[@bindvalue='provinceCode']//span[contains(@class, 'ng-value-label')]")).getText();
    }


}
