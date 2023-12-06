package ru.george.booksharing.server.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.george.booksharing.server.controller.dto.CreateUserRequestBody;
import ru.george.booksharing.server.controller.dto.GetUserByCredentialsRequestBody;
import ru.george.booksharing.server.model.User;
import ru.george.booksharing.server.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(@NotNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(@NotNull CreateUserRequestBody requestBody) {
        User user = userRepository.findByUsername(requestBody.username());
        if (user != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with this username already exist");

        return userRepository.save(
                new User(
                        0,
                        requestBody.username(),
                        requestBody.password(),
                        requestBody.role()
                )
        );
    }

    public User getByCredentials(@NotNull GetUserByCredentialsRequestBody requestBody) {
        return userRepository.findByUsernameAndPassword(requestBody.username(), requestBody.password());
    }
}
