package com.simbirsoft.itplacetestng;


import com.simbirsoft.itplacetestng.pages.ItplacePage;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;


/**
 * Created by user on 02.12.2016.
 */

public class SimpleTest extends DriveInit{

    ItplacePage itplace;

    protected void derivedSetUp()
    {
        itplace = new ItplacePage(driver);
    }


    @Features("Тесты на TestNG")
    @Stories("Первый тест")
    @Test
    public void case00() throws Exception {
        itplace.openItPlaceMainPageWithCaptcha();
        itplace.loadAllField();
        itplace.mouseClickByLocator(itplace.sendButton);
        itplace.findRedRabbit();
        System.out.println("Case is passed. http://itplace.simbirsoft.com/ Form with captcha worked");
    }

    @Features("Тесты на TestNG")
    @Stories("Второй тест")
    @Test
    public void case01() {
        System.out.println("Webdriver at test: " + driver);
        itplace.openItPlaceMainPage();
        itplace.checkRndEnrolButton();
        System.out.println("Case is passed. http://itplace.simbirsoft.com/ Form without captcha worked");
    }


}
