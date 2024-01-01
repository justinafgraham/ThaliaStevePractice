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
    @Autowired
    private HomeController sut;


//    public HomeControllerTest(StoryItemsRepo storyItemsRepo) {
//        this.storyItemsRepo = storyItemsRepo;
//    }

    int itemCount = 10;

    @BeforeEach
    public void setup() {
    }



    @Test
    void testGetTopNewsHttpStream() {
//        0.7
        var actual = sut.getTop10HttpStream();
        assertThat(actual.size()).isEqualTo(itemCount);
    }

}
