package com.simbirsoft.itplacetestng.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.yandex.qatools.allure.annotations.Step;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.assertEquals;

/**
 * Created by user on 02.12.2016.
 */
public class ItplacePage {
    private static WebDriver driver;

    public ItplacePage (WebDriver driver) {
        System.out.println("Webdriver: " + driver);
        this.driver = driver;
        System.out.println("Webdriver: " + this.driver);
        PageFactory.initElements(driver,this);
    }

    //элементы управление страницы
    public String itplaceMainPageWithCaptha = "http://itplace.simbirsoft.com";
    public String itplaceMainPageWithoutCaptha = "http://itplace.simbirsoft.com/?mode=test";
    public String sendButton = "//*[@id='contactformid']//button";
    //адреса полей
    public String nameField = "//*[@id='name']";
    public String secondNameField = "//*[@id='familyName']";
    public String telefonField = "//*[@id='phone']";
    public String emailField = "//*[@id='email']";
    public String cityField = "//*[@id='city']";
        // значения полей
    public String nameUser = "Santa";
    public String secondName = "Claus";
    public String telefonNumber = "1234567890";
    public String emailUser = "test@mail.ru";
    public String cityUser = "Lapland";

    // значение переменных
    private boolean oneSimpleCheck;

    //-------------тыкаем по локатору с xpath-------------
    public void mouseClickByLocator(String xpathLocator) {
        String locator = xpathLocator;
        WebElement el = driver.findElement(By.xpath(locator));
        el.click();
    }

    //-------------заполнение одного поля-------------
    public void eatThisShitByLocator (String xpathLoactor, String nameUser){
    //функция где на вход две переменные. заполнить поле (путь до поля, текст с содержимым)
    driver.findElement(By.xpath(xpathLoactor)).sendKeys(nameUser);
    }

    //-------------заполнение всех полей-------------
    @Step("Заполнение полей")
    public void loadAllField(){
        eatThisShitByLocator(nameField,nameUser);
        eatThisShitByLocator(secondNameField, secondName);
        eatThisShitByLocator(telefonField, telefonNumber);
        eatThisShitByLocator(emailField, emailUser);
        eatThisShitByLocator(cityField,cityUser);
    }



    @FindBy(xpath = "//button[contains(@class,'programs__sign')]")
    private List<WebElement> programmButtons;

    //--------------  случайный выбор кнопки из массива -----------------
    @Step("Выбор кнопки из массива")
    public String selectRndEnrolButton(){
        List<WebElement> buttons = programmButtons;
        int l = buttons.size() - 1;
        //System.out.println("Число найденных элементов: " + l);
        Random rndbtn = new Random();
        int i=rndbtn.nextInt(l+1);
        WebElement rndProgramm= buttons.get(i);
        rndProgramm.click();
        String enrollCourse = rndProgramm.getAttribute("data-course");
        return enrollCourse;
    }
    //------------ассерт кейса 01 -------------
   public ItplacePage checkRndEnrolButton(){
        oneSimpleCheck = false;
        try {
            driver.get(itplaceMainPageWithoutCaptha);

            String rndCourse=selectRndEnrolButton();
            /**
             * Вот тут очень странная конструкция оюъявления PageFactory
             */
//            ItplacePage pfdriver = PageFactory.initElements(driver,ItplacePage.class);
//            String rndCourse=pfdriver.selectRndEnrolButton();

            System.out.println("Checking Complete Form with enroll is working");
            System.out.println("Select random Course: "+rndCourse);
            if (driver.findElement(By.id(rndCourse)).isSelected())
            {oneSimpleCheck = true;}
        }catch (Exception e) {
            System.out.println("FAILURE: http://itplace.simbirsoft.com/ Test form JOIN NOW is failure");
        }
        Assert.assertTrue(oneSimpleCheck);
        return this;
    }

    @Step("Переход на страницу")
    public ItplacePage openItPlaceMainPageWithCaptcha() {
        try {
            driver.get(itplaceMainPageWithCaptha);
        } catch (Exception e) {
            System.out.println("FAILURE: http://itplace.simbirsoft.com/ page is NOT opened");
        }
        return this;
    }
    @Step("Переход на страницу")
    public ItplacePage openItPlaceMainPage() {
        try {
            driver.get(itplaceMainPageWithoutCaptha);
        } catch (Exception e) {
            System.out.println("FAILURE: http://itplace.simbirsoft.com/?mode=test page is NOT opened");
        }
        return this;
    }


    //-------------ассерт кейса 00 -------------
    public void findRedRabbit(){
        assertEquals(driver.findElement(By.xpath(".//*[@id='captcha']")).getAttribute("class"),"full-width error1");
    }
}
