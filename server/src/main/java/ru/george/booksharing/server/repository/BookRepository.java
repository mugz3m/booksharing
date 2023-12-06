package ru.george.booksharing.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.george.booksharing.server.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
