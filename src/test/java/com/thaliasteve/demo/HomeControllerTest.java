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
    void testGetTopNewsItemsRetrofix() {
//        31.9
        List actual = sut.GetTopNewsItems();
        assertThat(actual.size()).isEqualTo(itemCount);
    }

    @Test
    void testGetTopNewsRestTemplate() {
// 33
        var actual = sut.getTop10RestTemplate();
        assertThat(actual.size()).isEqualTo(itemCount);
    }

    @Test
    void testGetTopNewsHttpStream() {
//        0.7
        var actual = sut.getTop10HttpStream();
        assertThat(actual.size()).isEqualTo(itemCount);
    }

    @Test
    void testGetTopNewsFutures() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
//        2.4 - 0.7 seconds
        var actual = sut.getTop10Futures();
        assertThat(actual.size()).isEqualTo(itemCount);
    }

    @Test
    void testGetTopNewsRxJava() {
        var actual = sut.getTopStoriesRxJava();
//        assertThat(actual.size()).isEqualTo(itemCount);
    }
}
