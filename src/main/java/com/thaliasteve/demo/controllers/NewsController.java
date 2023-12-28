package com.thaliasteve.demo.controllers;

import com.thaliasteve.demo.models.HackerNewsStory;
import com.thaliasteve.demo.services.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewsController {

    @Autowired
    HackerNewsService hackerNewsService;

    @GetMapping("/news")
    public List<HackerNewsStory> getTop10Stories() {
        return hackerNewsService.getTop10Stories();
    }
}
