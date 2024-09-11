package com.news.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.news.service.NewsService;

@RestController
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/api/news")
    public String getNews(@RequestParam String query, @RequestParam int page) {
        return newsService.getNews(query, page);
    }
}
