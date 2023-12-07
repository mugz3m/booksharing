package ru.george.booksharing.client.model.user;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import ru.george.booksharing.client.model.RequestBody;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static ru.george.booksharing.client.model.ApiConfig.API_URL;

public class GetUsersByCredentialsRequest {
    private final Gson gson = new Gson();

    public GetUserResponse makeRequest(@NotNull GetUsersByCredentialsBody requestBody) throws Exception {
        HttpResponse<String> response =
                HttpClient.newHttpClient().send(getHttpRequest(requestBody), HttpResponse.BodyHandlers.ofString());
        return makeRequestResponseFromJson(response.body());
    }

    private HttpRequest getHttpRequest(GetUsersByCredentialsBody requestBody) throws Exception {
        return HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/users/credentials"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(makeRequestBodyJson(requestBody)))
                .build();
    }

    private String makeRequestBodyJson(RequestBody requestBody) {
        return gson.toJson(requestBody);
    }

    private GetUserResponse makeRequestResponseFromJson(String json) {
        return gson.fromJson(json, GetUserResponse.class);
    }
}