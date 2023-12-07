package ru.george.booksharing.server.controller.dto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public record CreateBookRequestBody(
        @NotNull String title,
        @NotNull String author,
        @Nullable Integer year,
        @Nullable String genre,
        @NotNull Integer userId
) {
}
