package edu.selenium.cucumber.app;

import edu.selenium.cucumber.pages.Cart;
import edu.selenium.cucumber.pages.CartPage;
import edu.selenium.cucumber.pages.ItemPage;
import edu.selenium.cucumber.pages.MainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.DriverManager;

public class Application {
    public static WebDriver driver;
    public static WebDriverWait wait;
    MainPage mainPage;
    ItemPage itemPage;
    CartPage cartPage;
    Cart cart;

    public Application(){
        //System.setProperty("webdriver.edge.driver", "C:\\Tools\\msedgedriver.exe");
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver, 10);

        mainPage=new MainPage();
        itemPage=new ItemPage();
        cartPage=new CartPage();
        cart= new Cart();
    }
    public void quit(){driver.quit();
    }

    public void addItemsToCart(){
        for (int i = 0;i<3; i++) {
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
