package com.thaliasteve.demo;

import com.thaliasteve.demo.models.StoryItem;
import com.thaliasteve.demo.services.HackerNewsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
                .getTopStoriesRetrofit()
                .subscribe(items::add);

//        items.stream().forEach(item -> {
//            storyItemsRepo.save(item);
//        });

        return items;
    }

    public List<StoryItem> getTopStoriesRxJava() {
        var result = new HackerNewsService().getTopStoriesRxJava();
        return result;
    }

    public List<?> getTop10HttpStream() {
        var result = new HackerNewsService().getTop10HttpStream();

        return result;
    }

    public List<?> getTop10Futures() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        var result = new HackerNewsService().getTop10Futures();

        return result;
    }
}
