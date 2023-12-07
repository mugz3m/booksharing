package ru.george.booksharing.server.controller;

import org.springframework.web.bind.annotation.*;
import ru.george.booksharing.server.controller.dto.CreateUserRequestBody;
import ru.george.booksharing.server.controller.dto.GetUserByCredentialsRequestBody;
import ru.george.booksharing.server.controller.dto.GetUserResponse;
import ru.george.booksharing.server.model.User;
import ru.george.booksharing.server.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = {"application/json"})
    public User createUser(@RequestBody CreateUserRequestBody requestBody) {
        return userService.createUser(requestBody);
    }

    @GetMapping
    public List<GetUserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/credentials", consumes = {"application/json"})
    public User getByCredentials(@RequestBody GetUserByCredentialsRequestBody requestBody) {
        return userService.getByCredentials(requestBody);
    }
}
