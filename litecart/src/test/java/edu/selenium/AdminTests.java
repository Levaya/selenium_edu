package edu.selenium;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
            //заново находим список
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

    //@Test
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
            //получаем каждую строку отдельно
            WebElement row = getRows.get(i);
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
                //заново получаем список строк
                List<WebElement> rows= driver.findElements(By.cssSelector("tr.row"));
                getRows=rows;
            } else {
                continue;
            }
        }
    }

    //@Test
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

    //@Test
    public void test4(){
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //проверяем количество позиций создаваемого товара в каталоге
        driver.findElement(By.cssSelector("li#app-:nth-of-type(2)")).click();
        driver.findElement(By.cssSelector("#doc-catalog")).click();
        driver.findElement(By.xpath("//a[.='Rubber Ducks']")).click();
        List<WebElement> defaultDucks = driver.findElements(By.xpath("//a[.='Devil Duck']"));
        //переход на создание товара
        driver.findElement(By.cssSelector("[href*=edit_product]")).click();
        //Блок General
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("date_valid_to")));
        driver.findElement(By.xpath("//label[.=' Enabled']")).click();
        driver.findElement(By.name("name[en]")).sendKeys("Devil Duck");
        driver.findElement(By.name("code")).sendKeys("DD001");
        driver.findElement(By.xpath("//input[@data-name='Root']")).click();
        driver.findElement(By.xpath("//input[@data-name='Rubber Ducks']")).click();
        driver.findElement(By.xpath("//input[contains(@name,'product_groups[]') and contains (@value,'1-3')]")).click();
        driver.findElement(By.name("quantity")).sendKeys("3");
        String link= "DevilDuck.jpg";
        String path = Path.of(link).toAbsolutePath().toString();
        driver.findElement(By.name("new_images[]")).sendKeys(path);
        driver.findElement(By.name("date_valid_from")).sendKeys("20112021");
        driver.findElement(By.name("date_valid_to")).sendKeys("20112022");
        //Блок Information
        driver.findElement(By.xpath("//a[.='Information']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("meta_description[en]")));
        WebElement manufacturer = driver.findElement(By.name("manufacturer_id"));
        new Select(manufacturer).selectByValue("1");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean viverra quam nec enim vulputate fermentum. Etiam fringilla vestibulum dolor, vel viverra est placerat at. Donec non pretium ante. Nullam varius eleifend elit, vitae fermentum felis placerat dictum. Etiam fringilla turpis a maximus auctor. Nunc quis erat tellus. Nulla congue quis velit a consectetur. Integer eu lacus nec leo consequat bibendum vitae id eros. ");
        driver.findElement(By.name("head_title[en]")).sendKeys("Devil Duck");
        //Блок Prices
        driver.findElement(By.xpath("//a[.='Prices']")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("table-campaigns")));
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("30");
        WebElement currency= driver.findElement(By.name("purchase_price_currency_code"));
        new Select(currency).selectByValue("USD");
        driver.findElement(By.name("prices[USD]")).sendKeys("30");
        driver.findElement(By.name("prices[EUR]")).sendKeys("35");
        //сохраняем
        driver.findElement(By.name("save")).click();
        //проверяем
        driver.findElement(By.cssSelector("#doc-catalog")).click();
        driver.findElement(By.xpath("//a[.='Rubber Ducks']")).click();
        List<WebElement> createdDucks = driver.findElements(By.xpath("//a[.='Devil Duck']"));
        assert createdDucks.size()>defaultDucks.size();
    }

    //@Test
    public void test5(){
        driver.findElement(By.cssSelector("[href*=countries]")).click();
        driver.findElement(By.className("button")).click();
        //получаем id основного окна
        String originalWindow= driver.getWindowHandle();
        //создаем список ссылочных элементов
        List<WebElement> elements = new ArrayList<>();
        elements.add(driver.findElement(By.cssSelector("[href*=alpha-2] i")));
        elements.add(driver.findElement(By.cssSelector("[href*=alpha-3] i")));
        elements.add(driver.findElement(By.xpath("//strong[.='Tax ID Format']/..//i")));
        elements.add(driver.findElement(By.cssSelector("[href*=address-formats] i")));
        elements.add(driver.findElement(By.xpath("//strong[.='Postcode Format']/..//i")));
        elements.add(driver.findElement(By.cssSelector("[href*=currency] i")));
        elements.add(driver.findElement(By.cssSelector("[href*=calling_codes] i")));
        for (WebElement element:elements) {
            //переключаемся на основное окно
            driver.switchTo().window(originalWindow);
            //клик по ссылке
            element.click();
            //ожидаем появление окна
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            //получаем id окон
            Set<String> handles = driver.getWindowHandles();
            //удаляем id основного окна из списка
            handles.remove(originalWindow);
            //сохраняем id нового окна в переменную
            String newWindow = handles.iterator().next();
            //переключаемся на новое окно
            driver.switchTo().window(newWindow);
            //закрываем
            driver.close();
            //переключаемся на основное окно
            driver.switchTo().window(originalWindow);
        }
    }

    @Test
    public void test6(){
        driver.findElement(By.cssSelector("#box-apps-menu>li:nth-of-type(2)>a")).click();
        List<String> duckLocators = new ArrayList<>();
        duckLocators.add("//tr[@class='row'][4]//a[contains(., 'Duck')]");
        duckLocators.add("//tr[@class='row'][5]//a[contains(., 'Duck')]");
        duckLocators.add("//tr[@class='row'][6]//a[contains(., 'Duck')]");
        duckLocators.add("//tr[@class='row'][7]//a[contains(., 'Duck')]");
        duckLocators.add("//tr[@class='row'][8]//a[contains(., 'Duck')]");
        duckLocators.add("//tr[@class='row'][9]//a[contains(., 'Duck')]");
        duckLocators.add("//tr[@class='row'][10]//a[contains(., 'Duck')]");
        for (String duckLocator: duckLocators){
            driver.findElement(By.id("doc-catalog")).click();
            driver.findElement(By.xpath("//a[.='Rubber Ducks']")).click();
            driver.findElement(By.xpath("//a[.='Subcategory']")).click();
            driver.findElement(By.xpath(duckLocator)).click();
            List<LogEntry> logEntries = driver.manage().logs().get("browser").getAll();
            assert logEntries.isEmpty();
        }
    }
}





