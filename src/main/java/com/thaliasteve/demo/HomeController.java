package com.thaliasteve.demo;

import com.thaliasteve.demo.models.StoryItem;
import com.thaliasteve.demo.services.HackerNewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

//    @Autowired
//    HackerNewsService hackerNewsService;

    @GetMapping("/news")
    public List<StoryItem> GetTopNewsItems() {
        List<StoryItem> items = new ArrayList<>();
        new HackerNewsService()
                .getTopStories(10)
                .subscribe(items::add);

        return items;
    }
}
