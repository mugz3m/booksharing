package ru.george.booksharing.client.model.user;

import ru.george.booksharing.client.model.RequestResponse;
import ru.george.booksharing.client.model.book.GetBookResponse;

import java.util.Set;

public record GetUserResponse(
        Integer id,
        String username,
        String password,
        UserRole role,
        Set<GetBookResponse> books
) implements RequestResponse {
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(id).append(" ").append(username).append("\n");
        for (GetBookResponse books : books) {
            builder.append(books);
        }

        return builder.toString();
    }
}
