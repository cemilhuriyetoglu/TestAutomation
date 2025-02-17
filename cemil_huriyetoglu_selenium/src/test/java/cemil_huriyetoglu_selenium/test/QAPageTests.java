package cemil_huriyetoglu_selenium.test;

import cemil_huriyetoglu_selenium.base.BaseTest;
import cemil_huriyetoglu_selenium.page.CareersPage;
import cemil_huriyetoglu_selenium.page.HomePage;
import cemil_huriyetoglu_selenium.page.QAPage;
import cemil_huriyetoglu_selenium.util.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class QAPageTests extends BaseTest {

    @Test(description = "Filter jobs by Location: “Istanbul, Turkey”, and Department: “Quality Assurance”, check the presence of the job list")
    public void filterJob() {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        homePage.openCompanyDrowdownMenufromNavBar();
        homePage.openCareersPage();

        CareersPage careersPage = new CareersPage();
        careersPage.openQualityAssurancePage();

        QAPage qaPage = new QAPage();
        qaPage.verifyQaPageIsOpen();
        qaPage.filterJobs();
    }

    @Test(description = "Check that all jobs’ Position contains “Quality Assurance”, Department contains “Quality Assurance”, and Location contains “Istanbul, Turkey”")
    public void verifyFilteredQaJobList() {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        homePage.openCompanyDrowdownMenufromNavBar();
        homePage.openCareersPage();

        CareersPage careersPage = new CareersPage();
        careersPage.openQualityAssurancePage();

        QAPage qaPage = new QAPage();
        qaPage.verifyQaPageIsOpen();
        qaPage.filterJobs();
        qaPage.checkFilteredJobListDepartpent();
        qaPage.checkFilteredJobListLocation();
    }

    @Test(description = "Click the “View Role” button and check that this action redirects us to the Lever Application form page")
    public void verifyViewRoleRedirectToLever() {
        HomePage homePage = new HomePage();
        homePage.acceptCookies();
        homePage.openCompanyDrowdownMenufromNavBar();
        homePage.openCareersPage();

        CareersPage careersPage = new CareersPage();
        careersPage.openQualityAssurancePage();

        QAPage qaPage = new QAPage();
        qaPage.verifyQaPageIsOpen();
        qaPage.filterJobs();
        qaPage.checkFilteredJobListDepartpent();
        qaPage.checkFilteredJobListLocation();
        qaPage.checkViewRole();
    }
}
