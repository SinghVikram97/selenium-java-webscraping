package com.vikram.seleniumjavawebscraping.service;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ScraperService {
    private static final String URL = "https://relatedwords.org/relatedto/";

    private final ChromeDriver chromeDriver;

    @PostConstruct
    void invokeScrape(){
        scrape("hello");
    }

    public void scrape(final String value){
        chromeDriver.get(URL+value);
        WebElement words = chromeDriver.findElement(By.className("words"));
        List<WebElement> wordList = words.findElements(By.tagName("a"));
        wordList.forEach(word -> System.out.println(word.getText()));
        chromeDriver.quit();
    }
}
