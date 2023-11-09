package com.vikram.seleniumjavawebscraping.config;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfig {

    @PostConstruct
    void setDriver(){
        System.setProperty("webdriver.chrome.driver","/Users/vikramsinghbedi/Documents/dev/selenium-java-webscraping/chromedriver");
    }

    @Bean
    public ChromeDriver chromeDriver(){
        return new ChromeDriver();
    }

}
