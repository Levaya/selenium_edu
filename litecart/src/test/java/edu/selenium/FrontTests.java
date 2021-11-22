package edu.selenium;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FrontTests extends TestBase {
    @Before
    public void openHomePage(){
        driver.get("http://localhost/litecart/");
    }

    @Test
    public void test1(){
        //находим список всех изображений
        List<WebElement> images = driver.findElements(By.cssSelector("div.image-wrapper"));
        //создаем цикл для перебора изображений
        for (int i=0; i<images.size();i++){
            //для каждого изображения получаем список стикеров
            List<WebElement> sticker= images.get(i).findElements(By.tagName("div"));
            //проверяем количество стикеров
            assert sticker.size()==1;
        }

    }

}
