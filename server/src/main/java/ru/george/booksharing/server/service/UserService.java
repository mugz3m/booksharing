package ru.george.booksharing.server.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.george.booksharing.server.controller.dto.CreateUserRequestBody;
import ru.george.booksharing.server.controller.dto.GetUserByCredentialsRequestBody;
import ru.george.booksharing.server.controller.dto.GetUserResponse;
import ru.george.booksharing.server.model.Book;
import ru.george.booksharing.server.model.User;
import ru.george.booksharing.server.repository.BookRepository;
import ru.george.booksharing.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static ru.george.booksharing.server.controller.dto.DtoUtils.userToGetUserResponse;

@Service
public class UserService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public UserService(@NotNull BookRepository bookRepository, @NotNull UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public List<GetUserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<GetUserResponse> response = new ArrayList<>();
        for (User user : users) {
            List<Book> books = bookRepository.getByUserId(user.getId());
            response.add(userToGetUserResponse(user, books));
        }

        return response;
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
                        requestBody.role(),
                        null
                )
        );
    }

    public User getByCredentials(@NotNull GetUserByCredentialsRequestBody requestBody) {
        return userRepository.findByUsernameAndPassword(requestBody.username(), requestBody.password());
    }
}
