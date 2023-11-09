package com.vikram.seleniumjavawebscraping.controller;

import com.vikram.seleniumjavawebscraping.service.ScraperService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ScraperController {
    private final ScraperService scraperService;
    @GetMapping("/v1/scrape/{word}")
    public List<String> getSimilarWords(@PathVariable String word){
        return scraperService.scrape(word);
    }

    @GetMapping("/v2/scrape/{word}")
    public List<String> getSimilarWordsViaInput(@PathVariable String word){
        return scraperService.scrapeByPopulatingInputField(word);
    }
}
