package com.thaliasteve.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
public class HomeController {

    @Autowired HackerNewsService hackerNewsService;
    @GetMapping("/news")
    public TopNewsItems GetTopNewsItems() {

        return hackerNewsService.getTop10();
    }
}
