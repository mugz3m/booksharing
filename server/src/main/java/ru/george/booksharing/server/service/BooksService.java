package ru.george.booksharing.server.service;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.george.booksharing.server.controller.dto.CreateBookRequestBody;
import ru.george.booksharing.server.controller.dto.DeleteBookRequestBody;
import ru.george.booksharing.server.controller.dto.DtoUtils;
import ru.george.booksharing.server.controller.dto.GetBookResponse;
import ru.george.booksharing.server.model.Book;
import ru.george.booksharing.server.model.User;
import ru.george.booksharing.server.model.UserRole;
import ru.george.booksharing.server.repository.BookRepository;
import ru.george.booksharing.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

@Service
public class BooksService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BooksService(@NotNull BookRepository bookRepository, @NotNull UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Book createBook(@NotNull CreateBookRequestBody requestBody) {
        return bookRepository.save(
                new Book(
                        0,
                        requestBody.title(),
                        requestBody.author(),
                        requestBody.year(),
                        requestBody.genre(),
                        userRepository.getReferenceById(requestBody.userId())
                )
        );
    }

    public List<GetBookResponse> getBooks() {
        List<Book> books = bookRepository.findAll();
        ArrayList<GetBookResponse> response = new ArrayList<>();
        for (Book book : books) {
            response.add(DtoUtils.bookToGetBookResponse(book));
        }

        return response;
    }

    public void deleteBook(@NotNull Integer id, @NotNull DeleteBookRequestBody requestBody) throws Throwable {
        Book book = bookRepository.findById(id).orElseThrow((Supplier<Throwable>) () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with id " + id + " not found")
        );
        User user = userRepository.findById(requestBody.userId()).orElseThrow((Supplier<Throwable>) () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id " + id + " not found")
        );
        if (user.getRole() != UserRole.ADMIN)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only admin can delete books");
        bookRepository.delete(book);
    }
}
