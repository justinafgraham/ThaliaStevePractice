package com.thaliasteve.demo.services;

import com.thaliasteve.demo.models.HackerNewsStory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsServiceTest {
    HackerNewsService classUnderTest;

    @BeforeEach
    void setUp() {
        classUnderTest = new HackerNewsService();

    }

    @Test
    void get10IdsTest() {

        List<Integer> result = classUnderTest.get10Ids();

        assertThat(result).hasSize(10);
    }

    @Test
    void getTop10StoriesTest() {
       List<HackerNewsStory> result = classUnderTest.getTop10Stories();

       assertThat(result).hasSize(10);
       assertThat(result).extracting("author").isNotEmpty();
    }
}
