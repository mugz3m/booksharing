package ru.george.booksharing.server.controller;

import org.springframework.web.bind.annotation.*;
import ru.george.booksharing.server.controller.dto.CreateBookRequestBody;
import ru.george.booksharing.server.controller.dto.DeleteBookRequestBody;
import ru.george.booksharing.server.controller.dto.GetBookResponse;
import ru.george.booksharing.server.model.Book;
import ru.george.booksharing.server.service.BooksService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping
    public Book createBook(@RequestBody CreateBookRequestBody requestBody) {
        return booksService.createBook(requestBody);
    }

    @GetMapping
    public List<GetBookResponse> getBooks() {
        return booksService.getBooks();
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Integer id, @RequestBody DeleteBookRequestBody requestBody) throws Throwable {
        booksService.deleteBook(id, requestBody);
    }
}
