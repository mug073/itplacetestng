package com.simbirsoft.itplacetestng;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriveInit {

    public static WebDriver driver;
    public String REMOTE_URL = "192.168.60.229";

    public DriveInit(){driver = null;}

    @BeforeTest
    public void setUp() throws IOException {
        System.setProperty("webdriver.chrome.driver", "libs/chromedriver.exe");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        URL hubUrl = new URL("http://" + REMOTE_URL + ":4444/wd/hub");
        driver = new RemoteWebDriver(hubUrl, capabilities);
        System.out.println("Webdriver: " + driver);
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        derivedSetUp();
    }

    protected void derivedSetUp(){};

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
