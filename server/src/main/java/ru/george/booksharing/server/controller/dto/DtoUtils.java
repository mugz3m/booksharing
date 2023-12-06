package ru.george.booksharing.server.controller.dto;

import ru.george.booksharing.server.model.Book;

public class DtoUtils {
    public static GetBookResponse bookToGetBookResponse(Book book) {
        return new GetBookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getYear(),
                book.getGenre(),
                book.getUser().getId()
        );
    }
}
