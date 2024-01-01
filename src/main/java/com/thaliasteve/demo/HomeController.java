package com.thaliasteve.demo;

import com.thaliasteve.demo.models.NewsItem;
import com.thaliasteve.demo.services.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class HomeController {

    @Autowired
    HackerNewsService hackerNewsService;

//    @Autowired
//    private final StoryItemsRepo storyItemsRepo;

//    public HomeController(StoryItemsRepo storyItemsRepo) {
//        this.storyItemsRepo = storyItemsRepo;
//    }


    public List<?> getTop10HttpStream() {
        var result = new HackerNewsService().getTop10HttpClient();

        return result;
    }


}
