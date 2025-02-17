package cemil_huriyetoglu_selenium.util;

import cemil_huriyetoglu_selenium.base.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        //Take screenshot
        File screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.FILE);
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        String name = result.getName();
        String filepath = ("test-output/ScreenShots/screenshot_" + name + "_failed" + date + ".png");
        try {
            FileUtils.copyFile(screenshot, new File(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
