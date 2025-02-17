package cemil_huriyetoglu_selenium.page;

import cemil_huriyetoglu_selenium.base.BasePageUtil;
import org.openqa.selenium.By;

public class CareersPage extends BasePageUtil {
    private final By ready = By.xpath("//h1[text()='Ready to disrupt? ']");
    private final By locations = By.xpath("//ul[@class='glide__slides']");
    private final By teams = By.xpath("//div[@class='col-12 d-flex flex-wrap p-0 career-load-more']");
    private final By life = By.xpath("//div[@class='swiper-wrapper']");
    private final By seeAllTeams = By.xpath("//*[text()='See all teams']");
    private final By qualityAssurance = By.xpath("//*[text()='Quality Assurance']");

    public void verifyCareersPageIsOpen() {
        verifyElementDisplayed(ready);
        verifyElementDisplayed(locations);
        verifyElementDisplayed(teams);
        verifyElementDisplayed(life);
    }

    public void openQualityAssurancePage() {
        scrollToElement(seeAllTeams);
        jsClick(seeAllTeams);
        scrollToElement(qualityAssurance);
        jsClick(qualityAssurance);
    }
}
