package com.vikram.seleniumjavawebscraping.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class ScraperService {
    private static final String URL = "https://relatedwords.org/relatedto/";

    private final ChromeDriver chromeDriver;


    public List<String> scrape(final String value){
        chromeDriver.get(URL+value);
        WebElement words = chromeDriver.findElement(By.className("words"));
        List<WebElement> wordList = words.findElements(By.tagName("a"));
        return wordList.stream().map(WebElement::getText).toList();
    }

    public List<String> scrapeByPopulatingInputField(final String value){
        chromeDriver.get(URL);
        // get input field
        WebElement wordInputField = chromeDriver.findElement(By.id("query"));
        // input the word
        wordInputField.sendKeys(value);
        // click the button
        chromeDriver.findElement(By.id("search-button")).click();
        // wait some time
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        // do the same ie get words
        WebElement words = chromeDriver.findElement(By.className("words"));
        List<WebElement> wordList = words.findElements(By.tagName("a"));
        return wordList.stream().map(WebElement::getText).toList();
    }

    public List<String> scrapeByPopulatingInputFieldExplicitWait(final String value){
        // waits for a maximum duration of 20 seconds if not found throws exception
        // ExpectedCondition is invoked by the Selenium WebDriver every 500 ms until it returns success.
        final WebDriverWait webDriverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(20));

        chromeDriver.get(URL);
        // get input field
        WebElement wordInputField = chromeDriver.findElement(By.id("query"));
        // input the word
        wordInputField.sendKeys(value);
        // click the button
        chromeDriver.findElement(By.id("search-button")).click();
        // wait till element visible
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("words")));
        // do the same ie get words
        WebElement words = chromeDriver.findElement(By.className("words"));
        List<WebElement> wordList = words.findElements(By.tagName("a"));
        return wordList.stream().map(WebElement::getText).toList();
    }
}
