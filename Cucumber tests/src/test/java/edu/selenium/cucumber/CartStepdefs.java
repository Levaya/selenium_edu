package edu.selenium.cucumber;

import io.cucumber.java8.En;
import org.junit.Assert;
import org.openqa.selenium.By;

import static edu.selenium.cucumber.app.Application.driver;

public class CartStepdefs extends CucumberTestBase implements En {

    public CartStepdefs() {
        Given("^I open 'http://localhost/litecart/'$", () -> {
            driver.get("http://localhost/litecart/");});
        When("^I add '(\\d+)' items to cart$", (Integer arg0) -> {
        app.addItemsToCart();});
        Then("cart has {string} items", (String s1) -> {
            Assert.assertTrue(driver.findElements(By.xpath(String.format("//span[@class='quantity' and contains(.,'%s')]", s1))).size()>0);
        });
        When("^I open cart$", () -> {
            app.openCart();
        });
        Then("^there are added items$", () -> {
            Assert.assertTrue(driver.findElements(By.cssSelector("#checkout-summary-wrapper")).size()>0);
        });
        When("^I delete all items$", () -> {
            app.removeItemsFromCart();
        });
        Then("^cart is empty$", () -> {
            Assert.assertTrue(driver.findElements(By.tagName("em")).size()>0);
        });
        After(() ->app.quit());

    }
}
