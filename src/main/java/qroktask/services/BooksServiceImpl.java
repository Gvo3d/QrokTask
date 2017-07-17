package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qroktask.dao.BooksRepository;
import qroktask.models.Book;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Iterable<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Book getOneBook(Integer id) {
        return booksRepository.findOne(id);
    }

    @Override
    @Transactional
    public Iterable<Book> getAllBooksFetchAll() {
        Iterable<Book> iterable = booksRepository.findAll();
        for (Book book:iterable){
            book.getAuthors().size();
        }
        return iterable;
    }

    @Override
    @Transactional
    public Book getOneBookFetchAll(Integer id) {
        Book book = booksRepository.findOne(id);
        book.getAuthors().size();
        return book;
    }

    @Override
    public Book create(Book book) {
        if (null!= book) {
            return booksRepository.save(book);
        } else return null;
    }

    @Override
    @Transactional
    public Book update(Book book) {
        if (null!= book) {
            Book fromDB = getOneBookFetchAll(book.getId());
            if (null!=fromDB){
                fromDB.setAuthors(book.getAuthors());
                fromDB.setGenre(book.getGenre());
                fromDB.setTitle(book.getTitle());
                fromDB.setIsbn(book.getIsbn());
                return booksRepository.saveAndFlush(fromDB);
            }
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        if (null != id) {
            return booksRepository.exists(id);
        } else return false;
    }

    @Override
    public Boolean delete(Integer id) {
        if (null != id) {
            if (booksRepository.exists(id)) {
                booksRepository.delete(id);
                return true;
            }
        }
        return false;
    }
}
