package com.thaliasteve.demo.services;

import com.thaliasteve.demo.models.StoryItem;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

@Service
public class HackerNewsService {
    private final HackerNewsApi hackerNewsApi;

    /* TODO: 12/23/23, stephen.wille; how to autowire constructor */
    public HackerNewsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hacker-news.firebaseio.com/v0/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        hackerNewsApi = retrofit.create(HackerNewsApi.class);
    }

    public Observable<StoryItem> getTopStories(int topLimit) {
        return hackerNewsApi.listOfTopStoryIDs()
                .flatMapIterable(x -> x)
                .limit(topLimit)
                .flatMap(itemId -> hackerNewsApi.getStoryItem(itemId.toString()));
    }
}


