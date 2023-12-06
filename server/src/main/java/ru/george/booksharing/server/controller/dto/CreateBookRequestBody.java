package ru.george.booksharing.server.controller.dto;

import org.jetbrains.annotations.NotNull;

public record CreateBookRequestBody(
        @NotNull String title,
        @NotNull String author,
        Integer year,
        String genre,
        @NotNull Integer userId
) {
}
