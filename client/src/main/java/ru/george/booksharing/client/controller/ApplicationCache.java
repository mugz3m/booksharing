package ru.george.booksharing.client.controller;

import ru.george.booksharing.client.model.user.GetUserResponse;

public class ApplicationCache {
    private GetUserResponse user;

    public GetUserResponse getUser() {
        return user;
    }

    public void setUser(GetUserResponse user) {
        this.user = user;
    }
}
