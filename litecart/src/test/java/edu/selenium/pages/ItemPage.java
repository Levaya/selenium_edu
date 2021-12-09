package edu.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;

import static edu.selenium.tests.TestBase.driver;

public class ItemPage {
    @FindBy (tagName = "select")
    public WebElement selectSize;

    @FindBy (name = "add_cart_product")
    public WebElement addToCart;

    @FindBy (className = "fa-home")
    public WebElement goHome;

    public boolean hasOptions(){
        try {
            driver.findElement(By.cssSelector(".options"));
            return true;
        }
        catch (NoSuchElementException ex){return false;}
    }

    public ItemPage selectOption(int index){
        new Select(selectSize).selectByIndex(index);
        return this;
    }

}
