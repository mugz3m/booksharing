package ru.george.booksharing.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;
import ru.george.booksharing.server.controller.dto.CreateUserRequestBody;
import ru.george.booksharing.server.controller.dto.GetUserByCredentialsRequestBody;
import ru.george.booksharing.server.model.User;
import ru.george.booksharing.server.model.UserRole;
import ru.george.booksharing.server.repository.BookRepository;
import ru.george.booksharing.server.repository.UserRepository;
import ru.george.booksharing.server.service.UserService;

@SpringBootTest
public class UsersServiceTest {
    private final User userMock = new User(1, "test", "password", UserRole.USER);
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public UsersServiceTest(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        userService = new UserService(userRepository);
    }

    @BeforeEach
    public void initDatabase() {
        bookRepository.deleteAll();
        userRepository.deleteAll();
        userRepository.save(userMock);
    }

    @Test
    public void createUserTest() {
        String username = "username";
        String password = "password";
        UserRole role = UserRole.USER;

        CreateUserRequestBody requestBody = new CreateUserRequestBody(username, password, role);
        User user = userService.createUser(requestBody);

        Assertions.assertEquals(user.getUsername(), username);
        Assertions.assertEquals(user.getPassword(), password);
        Assertions.assertEquals(user.getRole(), role);
    }

    @Test
    public void createExistUserTest() {
        String password = "password";
        UserRole role = UserRole.USER;

        CreateUserRequestBody requestBody = new CreateUserRequestBody(userMock.getUsername(), password, role);
        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> userService.createUser(requestBody),
                "User with this username already exist"
        );
    }

    @Test
    public void getByCredentialsFoundTest() {
        GetUserByCredentialsRequestBody requestBody =
                new GetUserByCredentialsRequestBody(userMock.getUsername(), userMock.getPassword());
        User user = userService.getByCredentials(requestBody);

        Utils.assertUsersByContent(userMock, user);
    }

    @Test
    public void getByCredentialsNotFoundTest() {
        GetUserByCredentialsRequestBody requestBody = new GetUserByCredentialsRequestBody("admin", "admin");
        User user = userService.getByCredentials(requestBody);
        Assertions.assertNull(user);
    }
}
