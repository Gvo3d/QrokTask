package qroktask.services;

import qroktask.models.Book;

public interface BooksService {
    public Iterable<Book> getAllBooks();
    public Book getOneBook(Integer id);

}
