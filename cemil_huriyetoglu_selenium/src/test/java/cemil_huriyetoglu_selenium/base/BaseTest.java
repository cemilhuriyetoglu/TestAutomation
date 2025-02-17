package cemil_huriyetoglu_selenium.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod
    public void setup() {
        new BasePageUtil()
                .openBrowser();
    }

    @AfterMethod
    public void tearDown() {
        new BasePageUtil()
                .closeBrowser();
    }
}
