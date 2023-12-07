package ru.george.booksharing.client.model.user;

import org.jetbrains.annotations.NotNull;
import ru.george.booksharing.client.model.RequestBody;

public record GetUsersByCredentialsBody(
        @NotNull String username,
        @NotNull String password
) implements RequestBody {
}
