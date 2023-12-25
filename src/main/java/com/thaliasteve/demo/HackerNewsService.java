package com.thaliasteve.demo;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.net.http.HttpResponse.BodyHandlers.ofString;
import static java.util.stream.Collectors.toList;

@Service
public class HackerNewsService {

    public TopNewsItems getTop10() {
        List<Integer> topIds = Arrays.stream(Objects.requireNonNull(
                new RestTemplate().getForEntity("https://hacker-news.firebaseio.com/v0/topstories.json",
                                                Integer[].class).getBody())).limit(10).toList();

        HttpClient client = HttpClient.newHttpClient();
        List<HttpRequest> requests = topIds.stream()
                .map(itemId -> {
                    try {
                        return new URI("https://hacker-news.firebaseio.com/v0/item/" + itemId + ".json");
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                })
                .map(uri -> HttpRequest.newBuilder().uri(uri))
                .map(HttpRequest.Builder::build)
                .toList();

        List<HttpResponse<String>> listOfCompletableFutures = requests.stream()
                .map(request -> client.sendAsync(request, ofString()))
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .toList();

        return null;
    }
}
