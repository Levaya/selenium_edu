package edu.selenium.blocks;

import edu.selenium.pages.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Cart extends Page {
    @FindBy (css = "#cart .link")
    public WebElement goToCart;

    public Cart (WebDriver driver){
        super();
        PageFactory.initElements(driver, this);
    }
}
