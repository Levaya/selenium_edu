package edu.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static edu.selenium.tests.TestBase.wait;
import static edu.selenium.tests.TestBase.driver;

public class CartPage extends Page {
    @FindBy (name = "remove_cart_item")
    public WebElement removeItemFromCart;

    public CartPage (WebDriver driver){
        super();
        PageFactory.initElements(driver, this);
    }

    public boolean cartIsNotEmpty(){
        if (driver.findElements(By.cssSelector(".dataTable td.item")).size()>0)
        {return true;} else {return false;}
    }

    WebElement dataTable;
    public void checkTable(){
        dataTable=driver.findElement(By.className("dataTable"));
    }

    public void waitVisibilityOfRemoveButton(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("remove_cart_item")));
    }

    public void waitStalenessOfTable(){
        wait.until(ExpectedConditions.stalenessOf(dataTable));
    }
}
