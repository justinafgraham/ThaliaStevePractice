package com.thaliasteve.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class HomeControllerTest {

    /*sut system under test */
    @Autowired
    private HomeController sut;

    @BeforeEach
    public void setup() {
//        this.sut = new HomeController();
    }


    @Test
    void testGetTopNewsItems() {
        TopNewsItems items = sut.GetTopNewsItems();
        assertThat(items.count()).isEqualTo(10);
    }
}
