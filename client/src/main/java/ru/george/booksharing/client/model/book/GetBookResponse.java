package ru.george.booksharing.client.model.book;

import ru.george.booksharing.client.model.RequestResponse;

public record GetBookResponse(
        Integer id,
        String title,
        String author,
        Integer year,
        String genre,
        Integer userId
) implements RequestResponse {
    @Override
    public String toString() {
        return "\t\t" + id + " " + author + " - " + "\"" + title + "\", " + year + ". " + genre + ".";
    }
}
