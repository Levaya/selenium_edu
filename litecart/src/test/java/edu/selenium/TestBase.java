package edu.selenium;

import jdk.jfr.Timespan;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Class.forName;
import static org.openqa.selenium.Platform.*;

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
//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
//        driver = new RemoteWebDriver(new URL("http://192.168.1.64:4444"), new InternetExplorerOptions());
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }

}
