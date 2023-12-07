package ru.george.booksharing.server.controller;

import org.springframework.web.bind.annotation.*;
import ru.george.booksharing.server.controller.dto.CreateBookRequestBody;
import ru.george.booksharing.server.model.Book;
import ru.george.booksharing.server.service.BooksService;

@RestController
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @PostMapping(consumes = {"application/json"})
    public Book createBook(@RequestBody CreateBookRequestBody requestBody) {
        return booksService.createBook(requestBody);
    }

    @DeleteMapping(value = "/{id}", consumes = {"application/json"})
    public void deleteBook(@PathVariable Integer id) throws Throwable {
        booksService.deleteBook(id);
    }
}
