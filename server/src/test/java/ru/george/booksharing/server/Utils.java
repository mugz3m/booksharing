package ru.george.booksharing.server;

import ru.george.booksharing.server.controller.dto.GetBookResponse;
import ru.george.booksharing.server.controller.dto.GetUserResponse;
import ru.george.booksharing.server.model.User;

import java.util.Objects;

public class Utils {
    public static void assertBooksByContent(GetBookResponse expected, GetBookResponse actual) {
        if (!Objects.equals(expected.id(), actual.id()) &&
                !Objects.equals(expected.title(), actual.title()) &&
                !Objects.equals(expected.author(), actual.author()) &&
                !Objects.equals(expected.year(), actual.year()) &&
                !Objects.equals(expected.genre(), actual.genre()) &&
                !Objects.equals(expected.userId(), actual.userId())) {
            throw new AssertionError("Expected " + expected + ", found " + actual);
        }

    }

    public static void assertUsersByContent(User expected, User actual) {
        if (!Objects.equals(expected.getId(), actual.getId()) &&
                !Objects.equals(expected.getUsername(), actual.getUsername()) &&
                !Objects.equals(expected.getPassword(), actual.getPassword()) &&
                !Objects.equals(expected.getRole(), actual.getRole())) {
            throw new AssertionError("Expected " + expected + ", found " + actual);
        }
    }

    public static void assertGetUsersResponseByContent(GetUserResponse expected, GetUserResponse actual) {
        if (!Objects.equals(expected.id(), actual.id()) &&
                !Objects.equals(expected.username(), actual.username()) &&
                !Objects.equals(expected.role(), actual.role())) {
            throw new AssertionError("Expected " + expected + ", found " + actual);
        }
    }
}
