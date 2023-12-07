package ru.george.booksharing.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.george.booksharing.server.model.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getByUserId(Integer userId);
}
