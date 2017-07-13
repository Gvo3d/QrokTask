package qroktask.services;

import qroktask.models.Book;

public interface BooksService {
    Iterable<Book> getAllBooks();
    Book getOneBook(Integer id);
    Iterable<Book> getAllBooksFetchAll();
    Book getOneBookFetchAll(Integer id);
    Book create(Book book);
    Book update(Book book);
    Boolean exists(Integer id);
    Boolean delete(Integer id);
}
