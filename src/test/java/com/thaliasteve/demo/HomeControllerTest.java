package com.thaliasteve.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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

    @BeforeEach
    public void setup() {
        this.sut = new HomeController(storyItemsRepo);
    }


    @Test
    void testGetTopNewsItems() {
        List items = sut.GetTopNewsItems();
        assertThat(items.size()).isEqualTo(10);
    }

}
