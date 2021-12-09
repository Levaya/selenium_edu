package edu.selenium.blocks;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Cart {
    @FindBy (css = "#cart .link")
    public WebElement goToCart;
}
