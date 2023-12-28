package com.thaliasteve.demo.services;

import com.thaliasteve.demo.dto.HackerNewsStoryDTO;
import com.thaliasteve.demo.models.HackerNewsStory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class HackerNewsService {

    private static final String HACKER_NEWS_URL_BASE = "https://hacker-news.firebaseio.com/v0/";
    private RestTemplate restTemplate;

    public HackerNewsService() {
        this.restTemplate = new RestTemplate();
    }

    public List<Integer> get10Ids() {
        final String topStories = "topstories.json";
        Integer[] ids = restTemplate.getForEntity(HACKER_NEWS_URL_BASE + topStories, Integer[].class).getBody();
        return Arrays.stream(Objects.requireNonNull(ids)).limit(10).toList();
    }

    public List<HackerNewsStoryDTO> getTop10DTOs() {
        List<Integer> ids = get10Ids();
        List<HackerNewsStoryDTO> stories = new ArrayList<>();
        for (Integer id : ids) {
            final String item = "item/" + id + ".json";
            HackerNewsStoryDTO story = restTemplate.getForEntity(HACKER_NEWS_URL_BASE + item, HackerNewsStoryDTO.class).getBody();
            stories.add(story);
        }
        return stories;
    }

    public List<HackerNewsStory> getTop10Stories() {
        return getTop10DTOs().stream().map(story -> {
            HackerNewsStory hns = new HackerNewsStory();
            hns.setType(story.getType());
            hns.setAuthor(story.getBy());
            hns.setTitle(story.getTitle());
            hns.setUrl(story.getUrl());
            return hns;
        }).toList();
    }
}
