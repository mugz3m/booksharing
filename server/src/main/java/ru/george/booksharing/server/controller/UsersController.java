package ru.george.booksharing.server.controller;

import org.springframework.web.bind.annotation.*;
import ru.george.booksharing.server.controller.dto.CreateUserRequestBody;
import ru.george.booksharing.server.controller.dto.GetUserByCredentialsRequestBody;
import ru.george.booksharing.server.model.User;
import ru.george.booksharing.server.service.UserService;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequestBody requestBody) {
        return userService.createUser(requestBody);
    }

    @GetMapping
    public User getByUsernameAndPassword(@RequestBody GetUserByCredentialsRequestBody requestBody) {
        return userService.getByCredentials(requestBody);
    }
}
