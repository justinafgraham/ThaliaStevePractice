package com.thaliasteve.demo;

import com.thaliasteve.demo.models.StoryItem;
import com.thaliasteve.demo.services.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

//    @Autowired
//    HackerNewsService hackerNewsService;

//    @Autowired
    private final StoryItemsRepo storyItemsRepo;

    public HomeController(StoryItemsRepo storyItemsRepo) {
        this.storyItemsRepo = storyItemsRepo;
    }

    @GetMapping("/news")
    public List<StoryItem> GetTopNewsItems() {
        List<StoryItem> items = new ArrayList<>();
        new HackerNewsService()
                .getTopStories(10)
                .subscribe(items::add);

        items.stream().forEach(item->{
            System.out.println((char)27 + "[97;43m"+ item.toString() +(char)27+"[0m");
            storyItemsRepo.save(item);
        });

        System.out.println((char)27 + "[97;43m"+ storyItemsRepo +(char)27+"[0m");
        return items;
    }
}
