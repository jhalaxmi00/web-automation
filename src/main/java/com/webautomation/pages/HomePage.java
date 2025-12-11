package com.webautomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePage {
    private By heading = By.xpath("title");
    private By searchBox = By.name("q");
    private By searchButton = By.name("btnK");
    private By links = By.tagName("a");


    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getHeadingText() {
        return driver.findElement(heading).getText();
    }

    /** Type text into search box */
    public void enterSearchText(String text) {
        driver.findElement(searchBox).clear();
        driver.findElement(searchBox).sendKeys(text);
    }

    /** Click the search button */
    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public List<String> getAllLinkTexts() {
        List<WebElement> allLinks = driver.findElements(links);
        return allLinks.stream().map(WebElement::getText).collect(Collectors.toList());
    }

//    public List<String> getAllLinkTexts() {
//        List<WebElement> allLinks = driver.findElements(links);
//        List<String> linkTexts = new ArrayList<>();
//        for (WebElement link : allLinks) {
//            linkTexts.add(link.getText());
//        return linkTexts;
//    }

    /** Perform a complete search action */
    public void searchFor(String query) {
        enterSearchText(query);
        clickSearchButton();
    }

}
