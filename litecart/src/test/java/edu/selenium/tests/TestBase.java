package edu.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import static java.lang.Class.forName;

// Базовый класс теста
public class TestBase {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties props;


    public boolean isElementPresent (By locator){
        try {//wait.until((WebDriver d)->d.findElement(locator));
            driver.findElement(locator);
            return true;}
        catch (NoSuchElementException ex){return false;}
    }

    protected String getSaltString() {
        //создаем строку символов для email
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        //создаем конструктор строк
        StringBuilder salt = new StringBuilder();
        //создаем генератор чисел
        Random rnd = new Random();
        //цикл записывает 9 символов в конструктор строк
        while (salt.length() < 10) {
            //генерируем индекс
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            //добавляем символ в конструктор строк
            salt.append(SALTCHARS.charAt(index));
        }
        //преобразуем конструктор в строку
        String saltStr = salt.toString();
        return saltStr;
    }

    public void setDatepicker(WebDriver d, String cssSelector, String date)
    {
        new WebDriverWait(d, 30).until(driver -> driver.findElement(By.cssSelector(cssSelector)));
        ((JavascriptExecutor)d).executeScript(
            String.format("$('{0}').datepicker('setDate', '{1}')", cssSelector, date));
    }

    @Before
    public void init() throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        props=new Properties();
        //загружаем в объект props переменные из файла config.properties
        props.load(TestBase.class.getResourceAsStream("/config.properties"));
        //получаем строку со значением пути к выбранному драйверу
        String driverFromProperties = props.getProperty("driverName");
        //создаем тип, который содержит путь к драйверу
        Class classType = forName(driverFromProperties);
        //через приведение типов создаем новый объект выбранного драйвера
        driver = (WebDriver) classType.newInstance();
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }

}
