package ru.george.booksharing.server.controller.dto;

import ru.george.booksharing.server.model.Book;
import ru.george.booksharing.server.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DtoUtils {
    public static GetUserResponse userToGetUserResponse(User user, List<Book> books) {
        Set<GetBookResponse> getBookResponse = new HashSet<>();
        for (Book book : books) {
            getBookResponse.add(bookToGetBookResponse(book));
        }

        return new GetUserResponse(user.getId(), user.getUsername(), user.getRole(), getBookResponse);
    }

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
