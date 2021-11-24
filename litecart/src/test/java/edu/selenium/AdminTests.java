package edu.selenium;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AdminTests extends TestBase {
    @Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    //@Test
    public void test1() {
        //находим меню
        WebElement menu = driver.findElement(By.id("box-apps-menu-wrapper"));
        //находим список элементов в меню по имени класса
        List<WebElement> strings = menu.findElements(By.className("name"));
        //цикл для перебора элементов списка
        for (int i = 0; i < strings.size(); i++) {
            //заново находим список, теперь по css-селектору
            List<WebElement> s = driver.findElements(By.cssSelector("ul#box-apps-menu>li"));
            //перебираем элементы списка и кликаем
            s.get(i).click();
            //проверка наличия заголовка
            assertTrue(isElementPresent(By.tagName("h1")));
            //находим в каждом пункте список подпунктов
            List<WebElement> substrings = driver.findElements(By.cssSelector("li.selected>ul>li"));
            //цикл для перебора подпунктов
            for (int n = 0; n < substrings.size(); n++) {
                //заново находим сипсок подпунктов при каждой итерации
                List<WebElement> ss = driver.findElements(By.cssSelector("li.selected>ul>li"));
                //клик по подпункту
                ss.get(n).click();
                //проверка наличия заголовка
                assertTrue(isElementPresent(By.tagName("h1")));
            }
        }
    }

    @Test
    public void test2(){
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        //получаем список стран
        List<WebElement> countries= driver.findElements(By.cssSelector("tr.row a:not([title])"));
        //создаем список строк
        List<String> countryNames = new ArrayList<>();
        //создаем цикл для перебора стран
        for (int i=0; i<countries.size(); i++){
            //читаем название каждой страны в строку
            String s = countries.get(i).getText();
            //добавляем строку в список строк
            countryNames.add(s);
        }
        //создаем копию списка строк
        List<String> defaultCountries = countryNames;
        //сортируем список строк по алфавиту
        Collections.sort(countryNames);
        //сравниваем отсортированный список строк с копией (текстовый аналог списка веб-элементов)
        assert countryNames.equals(defaultCountries);
        //получаем список строк
        List<WebElement> getRows= driver.findElements(By.cssSelector("tr.row"));
        //цикл для перечисления строк
        for (int i=0; i<getRows.size();i++){
            //заново получаем список строк
            List<WebElement> rows= driver.findElements(By.cssSelector("tr.row"));
            //получаем каждую строку отдельно
            WebElement row = rows.get(i);
            //находим значение количества зон
            WebElement zoneValue=row.findElement(By.cssSelector("td:nth-of-type(6)"));
            //преобразуем веб-элемент в строку
            String s=zoneValue.getText();
            //преобразуем строку в число
            int zoneNumber = Integer.parseInt(s);
            //условие наличия зон в стране
            if (zoneNumber>0){
                //находим ссылку для перехода
                WebElement country=row.findElement(By.cssSelector("a:not([title])"));
                //клик по названию страны
                country.click();
                //получаем список зон по названию
                List<WebElement> zones= driver.findElements(By.cssSelector("table#table-zones tr:not(.header) input[type=hidden][value][name*=name]"));
                //создаем пустой список строк,чтобы загрузить в него названия зон
                List<String> zoneNames=new ArrayList<>();
                //цикл для перечисления зон
                for (int n=0;n<zones.size();n++){
                    //получаем название каждой зоны в виде строки
                    String z=zones.get(n).getText();
                    //добавляем название зоны в список строк
                    zoneNames.add(z);
                }
                //создаем копию списка зон
                List<String> zoneDefault=zoneNames;
                //сортируем список по алфавиту
                Collections.sort(zoneNames);
                //сравниваем списки зон
                assert zoneNames.equals(zoneDefault);
                //переходим на страницу со списком стран
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            } else {
                continue;
            }
        }
    }

    @Test
    public void test3(){
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        //получаем список стран
        List<WebElement> countries=driver.findElements(By.cssSelector("tr.row a:not([title])"));
        //цикл для перебора стран
        for (int i=0;i<countries.size();i++){
            //повторно находим список
            List<WebElement> countryNames=driver.findElements(By.cssSelector("tr.row a:not([title])"));
            //клик по названию страны
            countryNames.get(i).click();
            //получаем список зон
            List<WebElement> zones=driver.findElements(By.cssSelector("select[name*=zone_code]"));
            //создаем пустой список строк
            List<String> zoneList=new ArrayList<>();
            //цикл для перебора зон
            for (int n=0;n<zones.size();n++){
                //записываем имя каждой зоны в строку
                String zoneName=zones.get(n).getText();
                //добавляем название зоны в список строк
                zoneList.add(zoneName);
            }
            //создаем копию списка зон
            List<String> zoneDefault=zoneList;
            //сортируем список по алфавиту
            Collections.sort(zoneList);
            //сравниваем списки
            assert zoneDefault.equals(zoneList);
            //переходим на исходную страницу
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }
}





