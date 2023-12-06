package ru.george.booksharing.server.controller.dto;

import org.jetbrains.annotations.NotNull;
import ru.george.booksharing.server.model.UserRole;

public record CreateUserRequestBody(@NotNull String username, @NotNull String password, @NotNull UserRole role) {
}
