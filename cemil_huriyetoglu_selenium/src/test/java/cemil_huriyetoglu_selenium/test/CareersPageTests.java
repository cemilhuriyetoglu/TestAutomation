package cemil_huriyetoglu_selenium.test;

import cemil_huriyetoglu_selenium.base.BasePageUtil;
import cemil_huriyetoglu_selenium.base.BaseTest;
import cemil_huriyetoglu_selenium.page.CareersPage;
import cemil_huriyetoglu_selenium.page.HomePage;
import cemil_huriyetoglu_selenium.util.TestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class CareersPageTests extends BaseTest {

    @Test(description = "check Career page, its Locations, Teams, and Life at Insider blocks are open or not")
    public void checkCareersPage() {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        homePage.openCompanyDrowdownMenufromNavBar();
        homePage.openCareersPage();

        CareersPage careersPage = new CareersPage();
        careersPage.verifyCareersPageIsOpen();
    }
}
