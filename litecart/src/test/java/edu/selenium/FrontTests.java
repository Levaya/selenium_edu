package edu.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FrontTests extends TestBase {
    @Before
    public void start(){
        driver.get("http://localhost/litecart/");
    }

    //@Test
    public void test1(){
        //находим список всех изображений
        List<WebElement> images = driver.findElements(By.cssSelector("li.product"));
        //создаем цикл для перебора изображений
        for (int i=0; i<images.size();i++){
            //для каждого изображения получаем список стикеров
            List<WebElement> sticker= images.get(i).findElements(By.cssSelector(".sticker"));
            //проверяем количество стикеров
            assert sticker.size()==1;
        }
    }

    //@Test
    public void test2(){
        //находим название на главной странице
        WebElement yellowDuck= driver.findElement(By.cssSelector("#box-campaigns .name"));
        //получаем текст в строку
        String firstDuck = yellowDuck.getText();

        //получаем обычную цену
        WebElement regPrice=driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        //получаем текст в строку
        String rPrice=regPrice.getText();

        //получаем цвет обычной цены
        String colorRegPrice= driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("color");
        //создаем пустой список
        List<String> colorRegMatches = new ArrayList<String>();
        //создаем шаблон для поиска чисел
        Matcher matcher = Pattern.compile("\\d+").matcher(colorRegPrice);
        while(matcher.find())
            //добавляем в список строк числовые значения
            colorRegMatches.add(matcher.group());
        //приводим строки к числам
        int r = Integer.parseInt(colorRegMatches.get(0));
        int g = Integer.parseInt(colorRegMatches.get(1));
        int b = Integer.parseInt(colorRegMatches.get(2));
        //сверяем значения
        assert r==g && g==b;

        //получаем значение зачеркивания
        String deleted=driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("text-decoration-line");
        String line= "line-through";
        assert deleted.equals(line);

        //получаем акционную цену
        WebElement campPrice=driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));
        //получаем текст в строку
        String cPrice=campPrice.getText();

        //проверяем наличие тега strong у акционной цены
        assert isElementPresent(By.cssSelector("#box-campaigns strong.campaign-price"));

        //получаем цвет акционной цены
        String colorCampPrice= driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("color");
        //создаем пустой список
        List<String> colorCampMatches = new ArrayList<String>();
        //создаем шаблон для поиска чисел
        Matcher campMatcher = Pattern.compile("\\d+").matcher(colorCampPrice);
        while(campMatcher.find())
            //добавляем в список строк числовые значения
            colorCampMatches.add(campMatcher.group());
        //приводим строки к числам
        int gCamp = Integer.parseInt(colorCampMatches.get(1));
        int bCamp = Integer.parseInt(colorCampMatches.get(2));
        //сверяем значения
        assert gCamp==0 && bCamp==0;

        //получаем высоту обычной цены
        String heightRegPrice=driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getAttribute("offsetHeight");
        int heightRPrice=Integer.parseInt(heightRegPrice);
        //получаем ширину обычной цены
        String widthRegPrice=driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getAttribute("offsetWidth");
        int widthRPrice=Integer.parseInt(widthRegPrice);
        //получаем высоту акционной цены
        String heightCampPrice=driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getAttribute("offsetHeight");
        int heightCPrice=Integer.parseInt(heightCampPrice);
        //получаем ширину акционной цены
        String widthCampPrice=driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getAttribute("offsetWidth");
        int widthCPrice=Integer.parseInt(widthCampPrice);
        //проверяем размеры
        assert heightCPrice>heightRPrice;
        assert widthCPrice>widthRPrice;

        //клик по названию
        yellowDuck.click();
        //находим заголовок
        WebElement duckName=driver.findElement(By.cssSelector("h1.title"));
        //получаем текст в строку
        String headerName=duckName.getText();
        //находим обычную цену
        WebElement regularPrice=driver.findElement(By.cssSelector(".regular-price"));
        String rPriceCard=regularPrice.getText();

        //получаем цвет обычной цены
        String colorRegPriceCard= driver.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        //создаем пустой список
        List<String> colorRegCardMatches = new ArrayList<String>();
        //создаем шаблон для поиска чисел
        Matcher matcherCard = Pattern.compile("\\d+").matcher(colorRegPrice);
        while(matcherCard.find())
            //добавляем в список строк числовые значения
            colorRegCardMatches.add(matcherCard.group());
        //приводим строки к числам
        int rCard = Integer.parseInt(colorRegCardMatches.get(0));
        int gCard = Integer.parseInt(colorRegCardMatches.get(1));
        int bCard = Integer.parseInt(colorRegCardMatches.get(2));
        //сверяем значения
        assert rCard==gCard && gCard==bCard;

        //получаем значение зачеркивания
        String deletedCard=driver.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration-line");
        String lineCard= "line-through";
        assert deletedCard.equals(lineCard);

        //находим акционную цену
        WebElement campaignPrice=driver.findElement(By.cssSelector(".campaign-price"));
        String cPriceCard=campaignPrice.getText();

        //проверяем наличие тега strong у акционной цены
        assert isElementPresent(By.cssSelector("strong.campaign-price"));

        //получаем цвет акционной цены
        String colorCampPriceCard= driver.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        //создаем пустой список
        List<String> colorCampMatchesCard = new ArrayList<String>();
        //создаем шаблон для поиска чисел
        Matcher campMatcherCard = Pattern.compile("\\d+").matcher(colorCampPrice);
        while(campMatcherCard.find())
            //добавляем в список строк числовые значения
            colorCampMatchesCard.add(campMatcherCard.group());
        //приводим строки к числам
        int gCampCard = Integer.parseInt(colorCampMatchesCard.get(1));
        int bCampCard = Integer.parseInt(colorCampMatchesCard.get(2));
        //сверяем значения
        assert gCampCard==0 && bCampCard==0;

        //сравниваем строки
        assert firstDuck.equals(headerName);
        assert rPrice.equals(rPriceCard);
        assert cPrice.equals(cPriceCard);

        //получаем высоту обычной цены
        String heightRegPriceCard=driver.findElement(By.cssSelector(".regular-price")).getAttribute("offsetHeight");
        int heightRPriceCard=Integer.parseInt(heightRegPriceCard);
        //получаем ширину обычной цены
        String widthRegPriceCard=driver.findElement(By.cssSelector(".regular-price")).getAttribute("offsetWidth");
        int widthRPriceCard=Integer.parseInt(widthRegPriceCard);
        //получаем высоту акционной цены
        String heightCampPriceCard=driver.findElement(By.cssSelector(".campaign-price")).getAttribute("offsetHeight");
        int heightCPriceCard=Integer.parseInt(heightCampPriceCard);
        //получаем ширину акционной цены
        String widthCampPriceCard=driver.findElement(By.cssSelector(".campaign-price")).getAttribute("offsetWidth");
        int widthCPriceCard=Integer.parseInt(widthCampPriceCard);
        //проверяем размеры
        assert heightCPriceCard>heightRPriceCard;
        assert widthCPriceCard>widthRPriceCard;
    }

    @Test
    public void test3(){
        //клик по ссылке
        driver.findElement(By.cssSelector(".content table a")).click();
        //заполнение формы
        driver.findElement(By.cssSelector("[name=firstname]")).sendKeys("Ivan");
        driver.findElement(By.cssSelector("[name=lastname]")).sendKeys("Ivanov");
        driver.findElement(By.cssSelector("[name=address1]")).sendKeys("Privet Drive");
        driver.findElement(By.cssSelector("[name=postcode]")).sendKeys("12345");
        driver.findElement(By.cssSelector("[name=city]")).sendKeys("New York");
        WebElement countrySelect= driver.findElement(By.cssSelector("[name=country_code]"));
        new Select(countrySelect).selectByVisibleText("United States");
        WebElement zoneSelect=driver.findElement(By.cssSelector("select[name=zone_code]"));
        new Select(zoneSelect).selectByVisibleText("California");
        String email= getSaltString();
        driver.findElement(By.cssSelector("[name=email]")).sendKeys(email+"@gmail.com");
        WebElement phoneField=driver.findElement(By.name("phone"));
        phoneField.click();
        phoneField.sendKeys(Keys.HOME);
        phoneField.sendKeys("123456789");
        driver.findElement(By.name("password")).sendKeys("user");
        driver.findElement(By.name("confirmed_password")).sendKeys("user");
        //клик по кнопке Создать аккаунт
        driver.findElement(By.name("create_account")).click();
        //logout
        driver.findElement(By.xpath("//a[.='Logout']")).click();
        //заполнение полей для авторизации
        driver.findElement(By.name("email")).sendKeys(email+"@gmail.com");
        driver.findElement(By.name("password")).sendKeys("user");
        //login
        driver.findElement(By.name("login")).click();
        //logout
        driver.findElement(By.xpath("//a[.='Logout']")).click();
    }
}
