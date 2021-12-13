package edu.selenium.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cart extends Page{
    @FindBy (css = "#cart .link")
    public WebElement goToCart;

    public Cart (){
        super();
        PageFactory.initElements(driver, this);
    }
}
