package ru.george.booksharing.client.model.book;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import ru.george.booksharing.client.model.RequestBody;
import ru.george.booksharing.client.model.ApiConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CreateBookRequest {
    private final Gson gson = new Gson();

    public GetBookResponse makeRequest(@NotNull CreateBookBody requestBody) throws Exception {
        HttpResponse<String> response =
                HttpClient.newHttpClient().send(getHttpRequest(requestBody), HttpResponse.BodyHandlers.ofString());
        return makeRequestResponseFromJson(response.body());
    }

    private HttpRequest getHttpRequest(CreateBookBody requestBody) throws Exception {
        return HttpRequest.newBuilder()
                .uri(new URI(ApiConfig.API_URL + "/books"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(makeRequestBodyJson(requestBody)))
                .build();
    }

    private String makeRequestBodyJson(RequestBody requestBody) {
        return gson.toJson(requestBody);
    }

    private GetBookResponse makeRequestResponseFromJson(String json) {
        return gson.fromJson(json, GetBookResponse.class);
    }
}
