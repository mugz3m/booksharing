package ru.george.booksharing.client.model.book;

public class BookDataSource {
    private final CreateBookRequest createBookRequest = new CreateBookRequest();
    private final DeleteBookRequest deleteBookRequest = new DeleteBookRequest();

    public GetBookResponse createBook(CreateBookBody body) throws Exception {
        return createBookRequest.makeRequest(body);
    }

    public int deleteBook(int bookId) throws Exception {
        return deleteBookRequest.makeRequest(bookId);
    }
}
