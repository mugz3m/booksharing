package ru.george.booksharing.client.controller;

import ru.george.booksharing.client.model.user.*;

import java.util.List;

public class UserController {
    private final UserDataSource dataSource = new UserDataSource();
    private final ApplicationCache cache;

    public UserController(ApplicationCache applicationCache) {
        this.cache = applicationCache;
    }

    public boolean createUser(String username, String password, UserRole role) {
        try {
            GetUserResponse response = dataSource.createUser(new CreateUserBody(username, password, role));
            return response != null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean login(String username, String password) {
        try {
            GetUserResponse response = dataSource.getUsersByCredentials(new GetUsersByCredentialsBody(username, password));
            cache.setUser(response);
            return response != null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<GetUserResponse> getAllUsers() throws Exception {
        return dataSource.getAllUsers();
    }
}
