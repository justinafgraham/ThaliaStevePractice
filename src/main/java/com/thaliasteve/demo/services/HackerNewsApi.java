package com.thaliasteve.demo.services;

import com.thaliasteve.demo.models.StoryItem;
import io.reactivex.rxjava3.core.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

import java.util.List;

public interface HackerNewsApi {
    @GET("topstories.json")
    Observable<List<Integer>> listOfTopStoryIDs();

    @GET("item/{item-id}.json")
    Observable<StoryItem> getStoryItem(@Path("item-id") String itemId);

    @GET("item/{item-id}.json")
    StoryItem getStoryItemsFlowable(@Path("item-id") String itemId);

}
