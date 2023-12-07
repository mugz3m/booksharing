package ru.george.booksharing.client.controller;

import ru.george.booksharing.client.model.book.BookDataSource;
import ru.george.booksharing.client.model.book.CreateBookBody;
import ru.george.booksharing.client.model.book.GetBookResponse;

public class BookController {
    private final BookDataSource dataSource = new BookDataSource();

    public boolean createBook(String title, String author, int year, String genre, int userId) {
        try {
            GetBookResponse response =
                    dataSource.createBook(new CreateBookBody(title, author, year, genre, userId));
            return response != null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteBook(int bookId) {
        try {
            int statusCode = dataSource.deleteBook(bookId);
            return statusCode == 200;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
