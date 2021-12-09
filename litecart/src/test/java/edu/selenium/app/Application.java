package edu.selenium.app;

import edu.selenium.blocks.Cart;
import edu.selenium.pages.CartPage;
import edu.selenium.pages.ItemPage;
import edu.selenium.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static edu.selenium.tests.TestBase.wait;

public class Application {
    MainPage mainPage=new MainPage();
    ItemPage itemPage=new ItemPage();
    CartPage cartPage=new CartPage();
    Cart cart= new Cart();

    public void addItemsToCart(){
        for (String locator:mainPage.locators) {
            //клик по первому товару
            mainPage.firstItem.click();
            //добавление в корзину
            if (itemPage.hasOptions()){
                itemPage.selectOption(1).addToCart.click();
            }
            else {itemPage.addToCart.click();}
            //ждем изменения количества
            wait.until((WebDriver d) -> d.findElement(By.xpath(locator)));
            //домой
            itemPage.goHome.click();
        }
    }

    public void goToCart(){
        cart.goToCart.click();
    }

    public void removeItemsFromCart(){
        while (cartPage.cartIsNotEmpty()){
            //находим таблицу
            cartPage.checkTable();
            //ожидаем видимость кнопки remove
            cartPage.waitVisibilityOfRemoveButton();
            //клик по remove
            cartPage.removeItemFromCart.click();
            //ждем обновление таблицы
            cartPage.waitStalenessOfTable();
        }
    }
}
