package edu.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

import static java.lang.Class.forName;

// Базовый класс теста
public class TestBase {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties props;

    public boolean isElementPresent (By locator){
        try {driver.findElement(locator);
        return true;}
        catch (NoSuchElementException ex){return false;}
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
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop()
    {
        driver.quit();
        driver=null;
    }

}
