package ru.george.booksharing.client.model.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static ru.george.booksharing.client.model.ApiConfig.API_URL;

public class GetAllUsersRequest {
    private final Gson gson = new Gson();

    public List<GetUserResponse> makeRequest() throws Exception {
        HttpResponse<String> response =
                HttpClient.newHttpClient().send(getHttpRequest(), HttpResponse.BodyHandlers.ofString());
        return makeRequestResponseFromJson(response.body());
    }

    private HttpRequest getHttpRequest() throws Exception {
        return HttpRequest.newBuilder()
                .uri(new URI(API_URL + "/users"))
                .header("Content-Type", "application/json")
                .GET()
                .build();
    }

    private List<GetUserResponse> makeRequestResponseFromJson(String json) {
        Type type = new TypeToken<List<GetUserResponse>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
