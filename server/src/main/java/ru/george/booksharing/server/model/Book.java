package ru.george.booksharing.server.model;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String author;
    private Integer year;
    private String genre;
    @ManyToOne
    private User user;

    protected Book() {
    }

    public Book(@NotNull Integer id,
                @NotNull String title,
                @NotNull String author,
                Integer year,
                String genre,
                @NotNull User user) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public User getUser() {
        return user;
    }
}
