package com.thaliasteve.demo.services;

import com.google.gson.GsonBuilder;
import com.thaliasteve.demo.models.StoryItem;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subscribers.DisposableSubscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpResponse.BodyHandlers.ofString;

@Service
public class HackerNewsService {
    private final HackerNewsApi hackerNewsApi;
    private final String baseurl = "https://hacker-news.firebaseio.com/v0/";
    private final int TOP_LIMIT = 500;


    /* TODO: 12/23/23, stephen.wille; how to autowire constructor */
    public HackerNewsService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        hackerNewsApi = retrofit.create(HackerNewsApi.class);
    }

    public Observable<StoryItem> getTopStoriesRetrofit() {
        return hackerNewsApi.listOfTopStoryIDs()
                .flatMapIterable(x -> x)
                .take(TOP_LIMIT)
                .flatMap(itemId -> hackerNewsApi.getStoryItem(itemId.toString()));
    }

    public List<StoryItem> getTop10HttpStream() {
        List<Integer> topIds = Arrays.stream(Objects.requireNonNull(
                new RestTemplate().getForEntity(baseurl + "topstories.json",
                                                Integer[].class).getBody())).limit(TOP_LIMIT).toList();

        HttpClient client = HttpClient.newHttpClient();
        List<HttpRequest> requests = topIds.stream()
                .map(this::buildUri)
                .map(HttpRequest::newBuilder)
                .map(HttpRequest.Builder::build)
                .toList();

        List<StoryItem> storyItems = requests.parallelStream()
                .map(request -> {
                    try {
                        return client.send(request, ofString());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(Objects::nonNull)
                .map(item -> new GsonBuilder().create().fromJson(item.body(), StoryItem.class))
                .toList();

        return storyItems;
    }

    public List<StoryItem> getTop10Futures() throws URISyntaxException, IOException, InterruptedException, ExecutionException {
        HttpClient client = HttpClient.newHttpClient();

        List<Integer> topIds = Arrays.stream(Objects.requireNonNull(
                new RestTemplate().getForEntity(baseurl + "topstories.json",
                                                Integer[].class).getBody())).limit(TOP_LIMIT).toList();

        List<HttpRequest> requests = topIds.stream()
                .map(this::buildUri)
                .map(HttpRequest::newBuilder)
                .map(HttpRequest.Builder::build)
                .toList();


        var results = requests.parallelStream()
                .map(request -> client.sendAsync(request, ofString()))
                .map(future -> {
                    try {
                        return future.get();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(HttpResponse::body)
                .map(resp -> new GsonBuilder().create().fromJson(resp, StoryItem.class))
                .toList();

        return results;
    }


    public List<StoryItem> getTopStoriesRxJava() {
        List<Integer> topIds = Arrays.stream(Objects.requireNonNull(
                new RestTemplate().getForEntity(baseurl + "topstories.json",
                                                Integer[].class).getBody())).limit(TOP_LIMIT).toList();

        Flowable<StoryItem> items = Flowable.fromIterable(topIds)
                .map(this::buildUri)
                .parallel(10)
                .runOn(Schedulers.io())
                .map(itemUri -> {
                    var item =  new RestTemplate().getForEntity(itemUri, StoryItem.class).getBody();
                    return item;
                })
                .sequential();

        items.subscribe(x->{
            System.out.println((char)27 + "[97;43m"+ x.getId() +(char)27+"[0m");
        });

        return null;
    }


    private URI buildUri(Integer id) {
        try {
            return new URI(baseurl + "item/" + id + ".json");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}


