package edu.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;

import java.util.*;
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
            List<WebElement> sticker= images.get(i).findElements(By.cssSelector(".image-wrapper .sticker"));
            //проверяем количество стикеров
            assert sticker.size()==1;
        }
    }

    @Test
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
        WebElement regularPrice=driver.findElement(By.cssSelector("s.regular-price"));
        String rPriceCard=regularPrice.getText();
        //находим акционную цену
        WebElement campaignPrice=driver.findElement(By.cssSelector("strong.campaign-price"));
        String cPriceCard=campaignPrice.getText();
        //сравниваем строки
        assert firstDuck.equals(headerName);
        assert rPrice.equals(rPriceCard);
        assert cPrice.equals(cPriceCard);
    }
}
