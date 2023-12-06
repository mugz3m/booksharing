package ru.george.booksharing.server.controller.dto;

import org.jetbrains.annotations.NotNull;

public record DeleteBookRequestBody(@NotNull Integer userId) {
}
