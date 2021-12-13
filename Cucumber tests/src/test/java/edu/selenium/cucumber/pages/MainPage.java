package edu.selenium.cucumber.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class MainPage extends Page {

    @FindBy (css="#box-most-popular li:first-child")
    public WebElement firstItem;

    public MainPage (){
//        super();
        PageFactory.initElements(driver, this);
    }


    public String getLocator(int index) {
        List<String> locators = new ArrayList<>();
        locators.add("//span[@class='quantity' and contains(.,'1')]");
        locators.add("//span[@class='quantity' and contains(.,'2')]");
        locators.add("//span[@class='quantity' and contains(.,'3')]");
        return locators.get(index);
    }

//    public int getCartValue(){
//        List<String> locators = new ArrayList<>();
//        locators.add("//span[@class='quantity' and contains(.,'1')]");
//        locators.add("//span[@class='quantity' and contains(.,'2')]");
//        locators.add("//span[@class='quantity' and contains(.,'3')]");
//        return locators.size();
//    }

}