package ru.george.booksharing.server.model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

@Table(name = "users")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private UserRole role;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books;

    protected User() {
    }

    public User(
            @NotNull Integer id,
            @NotNull String username,
            @NotNull String password,
            @NotNull UserRole role,
            @Nullable Set<Book> books
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Set<Book> getBooks() {
        return books;
    }
}
