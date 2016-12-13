package com.simbirsoft.itplacetestng;


import com.simbirsoft.itplacetestng.pages.ItplacePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;

import java.io.IOException;


/**
 * Created by user on 02.12.2016.
 */

public class SimpleTest extends DriveInit{

    ItplacePage itplace;

    protected void derivedSetUp()
    {
        itplace = new ItplacePage(driver);
    }

    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {

            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            failAllureScreenshot(screenshot);
        }
    }

    @Attachment(value = "Page_Fail_Screenshot", type = "image/png")
    public byte[] failAllureScreenshot(byte[] screenShot) {
        return screenShot;
    }


    @Features("Test with TestNG")
    @Stories("First test")
    @Test
    public void case00() throws Exception {
        itplace.openItPlaceMainPageWithCaptcha();
        itplace.loadAllField();
        itplace.mouseClickByLocator(itplace.sendButton);
        itplace.findRedRabbit();
        System.out.println("Case is passed. http://itplace.simbirsoft.com/ Form with captcha worked");
    }

    @Features("Test with TestNG")
    @Stories("Second test")
    @Test
    public void case01() {
        System.out.println("Webdriver at test: " + driver);
        itplace.openItPlaceMainPage();
        itplace.checkRndEnrolButton();
        System.out.println("Case is passed. http://itplace.simbirsoft.com/ Form without captcha worked");
    }


}
