package ru.george.booksharing.server.controller.dto;

import org.jetbrains.annotations.NotNull;

public record GetUserByCredentialsRequestBody(@NotNull String username, @NotNull String password) {
}
