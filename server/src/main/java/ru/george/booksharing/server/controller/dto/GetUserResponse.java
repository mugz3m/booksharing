package ru.george.booksharing.server.controller.dto;

import ru.george.booksharing.server.model.UserRole;

import java.util.Set;

public record GetUserResponse(
        Integer id,
        String username,
        UserRole role,
        Set<GetBookResponse> books
) {
}
