package edu.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static edu.selenium.tests.TestBase.driver;

public class ItemPage extends Page{
    @FindBy (tagName = "select")
    public WebElement selectSize;

    @FindBy (name = "add_cart_product")
    public WebElement addToCart;

    @FindBy (className = "fa-home")
    public WebElement goHome;

    public ItemPage (WebDriver driver){
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean hasOptions(){
        if (driver.findElements(By.cssSelector(".options")).size()>0){return true;}
        else {return false;}
    }

    public ItemPage selectOption(int index){
        new Select(selectSize).selectByIndex(index);
        return this;
    }

}
