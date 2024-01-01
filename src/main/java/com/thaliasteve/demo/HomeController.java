package com.thaliasteve.demo;

import com.thaliasteve.demo.models.StoryItemDto;
import com.thaliasteve.demo.repositories.StoryItemRepo;
import com.thaliasteve.demo.services.HackerNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    HackerNewsService hackerNewsService;

    @Autowired
    private StoryItemRepo storyItemRepo;


//    public HomeController(StoryItemsRepo storyItemsRepo) {
//        this.storyItemsRepo = storyItemsRepo;
//    }


    public List<StoryItemDto> getTopStoryItems() {
        var result = new HackerNewsService().getTopStoryItems();
        storyItemRepo.saveAll(result);

        return result;
    }


    public List<StoryItemDto> getRestTemplate() {
        var result = new HackerNewsService().getRestTemplate();
        storyItemRepo.saveAll(result);

        return result;
    }
}
