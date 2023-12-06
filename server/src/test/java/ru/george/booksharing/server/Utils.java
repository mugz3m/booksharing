package ru.george.booksharing.server;

import ru.george.booksharing.server.controller.dto.GetBookResponse;
import ru.george.booksharing.server.model.User;

import java.util.Objects;

public class Utils {
    public static void assertBooksByContent(GetBookResponse a, GetBookResponse b) {
        if (!Objects.equals(a.id(), b.id()) &&
                !Objects.equals(a.title(), b.title()) &&
                !Objects.equals(a.author(), b.author()) &&
                !Objects.equals(a.year(), b.year()) &&
                !Objects.equals(a.genre(), b.genre()) &&
                !Objects.equals(a.userId(), b.userId())) {
            throw new AssertionError("Expected " + a + ", found " + b);
        }

    }

    public static void assertUsersByContent(User a, User b) {
        if (!Objects.equals(a.getId(), b.getId()) &&
                !Objects.equals(a.getUsername(), b.getUsername()) &&
                !Objects.equals(a.getPassword(), b.getPassword()) &&
                !Objects.equals(a.getRole(), b.getRole())) {
            throw new AssertionError("Expected " + a + ", found " + b);
        }
    }
}
