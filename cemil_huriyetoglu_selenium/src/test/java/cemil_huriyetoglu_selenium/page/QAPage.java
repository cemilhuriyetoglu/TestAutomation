package cemil_huriyetoglu_selenium.page;

import cemil_huriyetoglu_selenium.base.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class QAPage extends BasePageUtil {
    private final By qaTitle = By.xpath("//h1[contains(text(),'Quality Assurance')]");
    private final By seeAllQaJobs = By.xpath("//a[text()='See all QA jobs']");
    private final By filterByLocation = By.xpath("//span[@data-select2-id=1]");
    private final By filterIstanbul = By.xpath("//option[text()='Istanbul, Turkiye']");
    private final By locationIstanbul = By.xpath("//span[@title='Istanbul, Turkiye']");
    private final By filterByDepartment = By.xpath("//span[@data-select2-id=4]");
    private final By filterQa = By.xpath("//option[text()='Quality Assurance']");
    private final By departmentQA = By.xpath("//span[@title='Quality Assurance']");
    private final By jobList = By.xpath("//div[@id='jobs-list']");
    private final By filteredJobListDepartment = By.xpath("//span[contains(@class,'position-department')]");
    private final By filteredJobListLocation = By.xpath("//div[contains(@class,'position-location')]");
    private final By viewRolebtn = By.xpath("(//*[text()='View Role'])[1]");

    public void verifyQaPageIsOpen() {
        verifyElementDisplayed(qaTitle);
    }

    public void filterJobs() {
        scrollToElement(seeAllQaJobs);
        waitAndClick(seeAllQaJobs);
        wait(3000);
        scrollToElement(filterByLocation);
        waitAndClick(filterByLocation);
        wait(3000);
        waitAndClick(filterIstanbul);
        waitAndClick(filterByDepartment);
        wait(3000);
        waitAndClick(filterQa);
        waitAndClick(filterByDepartment);
        verifyElementDisplayed(locationIstanbul);
        verifyElementDisplayed(departmentQA);
        verifyElementDisplayed(jobList);
    }

    public void checkFilteredJobListDepartpent() {
        String department = "Quality Assurance";
        List<WebElement> positionsDepartment = findElements(filteredJobListDepartment);
        for (WebElement element : positionsDepartment) {
            Assert.assertEquals(
                    department,
                    element.getText(),
                    "Not all open positions contains " + department + ", " + "test failed.");
        }
    }

    public void checkFilteredJobListLocation() {
        String location = "Istanbul, Turkiye";
        List<WebElement> positionsLocation = findElements(filteredJobListLocation);
        for (WebElement element : positionsLocation) {
            Assert.assertEquals(
                    location,
                    element.getText(),
                    "Not all open positions contains " + location + ", " + "test failed.");
        }
    }

    public void checkViewRole() {
        String url = "jobs.lever.co/useinsider";
        mouseOverAndClick(viewRolebtn);
        switchTab(1);
        String currentUrl = getCurrentUrl();
        System.out.println(currentUrl);
        Assert.assertTrue(getCurrentUrl().contains(url), "Lever Application form page is open");
    }
}
