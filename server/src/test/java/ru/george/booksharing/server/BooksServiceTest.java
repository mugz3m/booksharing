package ru.george.booksharing.server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
import ru.george.booksharing.server.service.BooksService;

import java.util.List;

@SpringBootTest
public class BooksServiceTest {
    private final User userMock = new User(1, "test", "password", UserRole.USER);
    private final User adminMock = new User(2, "admin", "admin", UserRole.ADMIN);
    private final Book bookMock = new Book(1, "Title", "John Doe", 2023, "Fantastic", userMock);
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BooksService booksService;

    @Autowired
    public BooksServiceTest(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        booksService = new BooksService(bookRepository, userRepository);
    }

    @BeforeEach
    public void initDatabase() {
        bookRepository.deleteAll();
        userRepository.save(userMock);
        userRepository.save(adminMock);
        bookRepository.save(bookMock);
    }

    @Test
    public void createBookTest() {
        String title = "Test";
        String author = "Test";
        Integer year = 2023;
        String genre = "Test";

        CreateBookRequestBody requestBody = new CreateBookRequestBody(title, author, year, genre, userMock.getId());
        Book book = booksService.createBook(requestBody);

        Assertions.assertEquals(book.getTitle(), title);
        Assertions.assertEquals(book.getAuthor(), author);
        Assertions.assertEquals(book.getYear(), year);
        Assertions.assertEquals(book.getGenre(), genre);
        Utils.assertUsersByContent(book.getUser(), userMock);
    }

    @Test
    public void getBooksTest() {
        List<GetBookResponse> books = booksService.getBooks();
        Assertions.assertFalse(books.isEmpty());
        Assertions.assertEquals(books.size(), 1);
        Utils.assertBooksByContent(books.get(0), DtoUtils.bookToGetBookResponse(bookMock));
    }

    @Test
    public void deleteBookByUser() {
        DeleteBookRequestBody requestBody = new DeleteBookRequestBody(userMock.getId());
        Assertions.assertThrows(
                ResponseStatusException.class,
                () -> booksService.deleteBook(bookMock.getId(), requestBody),
                "Only admin can delete books"
        );
    }

    @Test
    public void deleteBookByAdmin() {
        DeleteBookRequestBody requestBody = new DeleteBookRequestBody(adminMock.getId());
        Assertions.assertDoesNotThrow(() -> booksService.deleteBook(bookMock.getId(), requestBody));

        Book removedBook = bookRepository.findById(bookMock.getId()).orElse(null);
        Assertions.assertNull(removedBook);
    }
}