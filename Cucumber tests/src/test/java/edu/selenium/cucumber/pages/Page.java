package edu.selenium.cucumber.pages;

import edu.selenium.cucumber.app.Application;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    public WebDriver driver;
    public WebDriverWait wait;
    public Page() {this.driver=Application.driver;
        this.wait=Application.wait;
    }
}