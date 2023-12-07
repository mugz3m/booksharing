package ru.george.booksharing.client.model.user;

import org.jetbrains.annotations.NotNull;
import ru.george.booksharing.client.model.RequestBody;

public record CreateUserBody(
        @NotNull String username,
        @NotNull String password,
        @NotNull UserRole role
) implements RequestBody {
}
