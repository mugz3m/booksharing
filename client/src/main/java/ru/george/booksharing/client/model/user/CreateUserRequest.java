package ru.george.booksharing.client.model.user;

import com.google.gson.Gson;
import org.jetbrains.annotations.NotNull;
import ru.george.booksharing.client.model.RequestBody;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import static ru.george.booksharing.client.model.ApiConfig.API_URL;

public class CreateUserRequest {
    private final Gson gson = new Gson();

    public GetUserResponse makeRequest(@NotNull CreateUserBody requestBody) throws Exception {
        HttpResponse<String> response =
                HttpClient.newHttpClient().send(getHttpRequest(requestBody), BodyHandlers.ofString());
        return makeRequestResponseFromJson(response.body());
    }

    private HttpRequest getHttpRequest(CreateUserBody requestBody) throws Exception {
        return HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/users"))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(makeRequestBodyJson(requestBody)))
                .build();
    }

    private String makeRequestBodyJson(RequestBody requestBody) {
        return gson.toJson(requestBody);
    }

    private GetUserResponse makeRequestResponseFromJson(String json) {
        return gson.fromJson(json, GetUserResponse.class);
    }
}
