package ru.george.booksharing.client.model.book;

import org.jetbrains.annotations.NotNull;
import ru.george.booksharing.client.model.ApiConfig;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteBookRequest {
    public int makeRequest(@NotNull Integer bookId) throws Exception {
        HttpResponse<String> response =
                HttpClient.newHttpClient().send(getHttpRequest(bookId), HttpResponse.BodyHandlers.ofString());
        return response.statusCode();
    }

    private HttpRequest getHttpRequest(Integer bookId) throws Exception {
        return HttpRequest.newBuilder()
                .uri(new URI(ApiConfig.API_URL + "/books/" + bookId))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();
    }
}
