package com.thaliasteve.demo.dto;

import lombok.Data;

@Data
public class HackerNewsStoryDTO {
    String by;
    Integer descendants;
    Integer id;
    Integer[] kids;
    Integer score;
    Integer time;
    String title;
    String type;
    String url;
}