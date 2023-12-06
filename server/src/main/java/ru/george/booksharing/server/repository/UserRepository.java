package ru.george.booksharing.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.george.booksharing.server.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
}
