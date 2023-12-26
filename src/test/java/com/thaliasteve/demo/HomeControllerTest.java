package com.thaliasteve.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HomeControllerTest {

    /*sut system under test */
    private HomeController sut;

    @Autowired
     StoryItemsRepo storyItemsRepo;

//    public HomeControllerTest(StoryItemsRepo storyItemsRepo) {
//        this.storyItemsRepo = storyItemsRepo;
//    }

    int itemCount = 500;

    @BeforeEach
    public void setup() {
        this.sut = new HomeController(storyItemsRepo);
    }


    @Test
    void testGetTopNewsItemsRx() {
//        31.9
        List result = sut.GetTopNewsItems();
        assertThat(result.size()).isEqualTo(itemCount);
    }

    @Test
    void testGetTopNewsHttpStream() {
//        32.8
        var result = sut.getTop10HttpStream();
        assertThat(result.size()).isEqualTo(itemCount);
    }
    @Test
    void testGetTopNewsFutures() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
//        0.9 seconds
        var result = sut.getTop10Futures();
        assertThat(result.size()).isEqualTo(itemCount);
    }
}
