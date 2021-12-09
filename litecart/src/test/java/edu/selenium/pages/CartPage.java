package edu.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.NoSuchElementException;
import static edu.selenium.tests.TestBase.wait;
import static edu.selenium.tests.TestBase.driver;

public class CartPage {
    @FindBy (name = "remove_cart_item")
    public WebElement removeItemFromCart;

    public boolean cartIsNotEmpty(){
        try{
            driver.findElement(By.name("remove_cart_item"));
            return true;
        }
        catch (NoSuchElementException ex){return  false;}
    }

    public void checkTable(){
        driver.findElement(By.className("dataTable"));
    }

    public void waitVisibilityOfRemoveButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("remove_cart_item")));
    }

    public void waitStalenessOfTable(){
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.className("dataTable"))));
    }
}
