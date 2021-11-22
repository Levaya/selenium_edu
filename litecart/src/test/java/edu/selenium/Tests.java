package edu.selenium;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.*;

public class Tests extends TestBase {

    @Test
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
}





