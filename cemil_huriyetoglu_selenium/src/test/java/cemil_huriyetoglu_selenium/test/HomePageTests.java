package cemil_huriyetoglu_selenium.test;

import cemil_huriyetoglu_selenium.base.BaseTest;
import cemil_huriyetoglu_selenium.page.HomePage;
import cemil_huriyetoglu_selenium.util.TestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestListener.class)
public class HomePageTests extends BaseTest {

    @Test(description = "check Insider home page is opened or not")
    public void checkHomePage() {
        HomePage homePage = new HomePage();
        homePage.verifyHomePageIsOpen();
    }
}
