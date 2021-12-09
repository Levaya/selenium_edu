package edu.selenium.app;

import edu.selenium.blocks.Cart;
import edu.selenium.pages.CartPage;
import edu.selenium.pages.ItemPage;
import edu.selenium.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static edu.selenium.tests.TestBase.driver;
import static edu.selenium.tests.TestBase.wait;

public class Application {
    MainPage mainPage=new MainPage(driver);
    ItemPage itemPage=new ItemPage(driver);
    CartPage cartPage=new CartPage(driver);
    Cart cart= new Cart(driver);

    public void addItemsToCart(){
        for (int i=0; i<3; i++) {
            //клик по первому товару
            mainPage.firstItem.click();
            //добавление в корзину
            if (itemPage.hasOptions()){
                itemPage.selectOption(1).addToCart.click();
            }
            else {itemPage.addToCart.click();}
            //ждем изменения количества
            int finalI = i;
            wait.until((WebDriver d) -> d.findElement(By.xpath(mainPage.getLocator(finalI))));
            //домой
            itemPage.goHome.click();
        }
    }

    public void openCart(){
        //wait.until(ExpectedConditions.visibilityOf(cart.goToCart));
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
