package edu.selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class MainPage {
    @FindBy (css="#box-most-popular li:first-child")
    public WebElement firstItem;

    public List<String> locators = new ArrayList<>();
    public void setLocators() {
        locators.add("//span[@class='quantity' and contains(.,'1')]");
        locators.add("//span[@class='quantity' and contains(.,'2')]");
        locators.add("//span[@class='quantity' and contains(.,'3')]");
    }
}
