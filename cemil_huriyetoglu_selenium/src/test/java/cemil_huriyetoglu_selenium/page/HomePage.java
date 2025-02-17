package cemil_huriyetoglu_selenium.page;

import cemil_huriyetoglu_selenium.base.BasePageUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePageUtil {

    private final By cookieAcceptButton = By.xpath("//*[@id='wt-cli-accept-all-btn']");
    private final By homePageTemplate = By.xpath("//body[contains(@class,'home page-template')]");
    private final By insiderLogo = By.xpath("//img[@alt='insider_logo']");
    private final By companyFromNavigationMenu = By.xpath("//a[contains(text(),'Company')]");
    private final By navBarDropdownMenu = By.xpath("//div[@class=\"dropdown-menu new-menu-dropdown-layout-6 show\"]");
    private final By careersFromNavBarDropdownMenu = By.xpath("//a[@class=\"dropdown-sub\" and text()='Careers']");


    public void acceptCookies() {
        try {
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(cookieAcceptButton));
            acceptButton.click();
        } catch (Exception e) {
            System.out.println("Cookie popup not found or already closed.");
        }
    }

    public void verifyHomePageIsOpen() {
        verifyElementDisplayed(homePageTemplate);
        verifyElementDisplayed(insiderLogo);
    }

    public void openCompanyDrowdownMenufromNavBar() {
        waitAndClick(companyFromNavigationMenu);
        verifyElementDisplayed(navBarDropdownMenu);
    }

    public void openCareersPage() {
        waitAndClick(careersFromNavBarDropdownMenu);
    }
}
