package edu.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.*;

public class FrontTests extends TestBase {

    @Test
    public void test1(){
        driver.get("http://localhost/litecart/");
        //находим список всех изображений
        List<WebElement> images = driver.findElements(By.cssSelector("li.product"));
        //создаем цикл для перебора изображений
        for (int i=0; i<images.size();i++){
            //для каждого изображения получаем список стикеров
            List<WebElement> sticker= images.get(i).findElements(By.cssSelector("div.image-wrapper>div"));
            //проверяем количество стикеров
            assert sticker.size()==1;
        }
    }
}
