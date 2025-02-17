package cemil_huriyetoglu_selenium.base;

import cemil_huriyetoglu_selenium.util.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePageUtil {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePageUtil() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
    }

    public void openBrowser() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.get("baseUrl"));
    }

    public void closeBrowser() {
        Driver.closeDriver();
    }

    public void wait(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void HighlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: red border: 1px dashed red;");
    }

    public void checkWebElementCount(By by) {
        try {
            if (driver.findElements(by).size() > 1) {
                Assert.fail("There are more than one WebElement of " + by.toString() + " . There has to be one.");
            } else if (driver.findElements(by).isEmpty()) {
                Assert.fail("Element not found: " + by);
            }
        } catch (InvalidSelectorException e) {
            Assert.fail(e.getMessage());
        }
    }

    public void waitAndClick(By by) {
        checkWebElementCount(by);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        WebElement element = driver.findElement(by);
        HighlightElement(element);
        element.click();
    }

    public void verifyElementDisplayed(By by) {
        try {
            Assert.assertTrue(driver.findElement(by).isDisplayed(), "Element not visible: " + by);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            Assert.fail("Element not found: " + by);
        }
    }

    public void jsClick(By by) {
        WebElement element = driver.findElement(by);
        HighlightElement(element);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public List<WebElement> findElements(By by) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return driver.findElements(by);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        String intoMiddle = "var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
                + "var elementTop = arguments[0].getBoundingClientRect().top;"
                + "window.scrollBy(0, elementTop-(viewPortHeight/2));";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(intoMiddle, element);
    }

    public void mouseOverAndClick(By by) {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(by);
        action.moveToElement(we).moveToElement(driver.findElement(by)).build().perform();
        action.click().build().perform();
    }

    public String getText(By by) {
        checkWebElementCount(by);
        String text = "";
        // Wait until element is visible
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        text = driver.findElement(by).getText();

        return text;
    }

    public void switchTab(int tabNumber) {
        ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(tabNumber));
    }

    public String getTabTitle() {
        return driver.getTitle();
    }
}
