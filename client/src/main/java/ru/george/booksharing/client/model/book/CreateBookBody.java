package ru.george.booksharing.client.model.book;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.george.booksharing.client.model.RequestBody;

public record CreateBookBody(
        @NotNull String title,
        @NotNull String author,
        @Nullable Integer year,
        @Nullable String genre,
        @NotNull Integer userId
) implements RequestBody {
}
