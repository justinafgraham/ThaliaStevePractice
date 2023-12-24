package com.thaliasteve.demo.services;

import com.thaliasteve.demo.models.StoryItem;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import java.util.List;

public interface HackerNewsApi {
    @GET("topstories.json")
    Observable<List<Integer>> listOfTopStoryIDs();

    @GET("item/{item-id}.json")
    Observable<StoryItem> getStoryItem(@Path("item-id") String itemId);
}
