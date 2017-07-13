package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qroktask.dao.BooksRepository;
import qroktask.models.Book;

import javax.persistence.EntityManager;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private EntityManager entityManager;

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
        return booksRepository.getAllBooksFetchAll(entityManager);
    }

    @Override
    @Transactional
    public Book getOneBookFetchAll(Integer id) {
        Optional<Book> result =  booksRepository.getBookFetchAll(entityManager,id);
        return result.orElse(null);
    }

    @Override
    public Book create(Book book) {
        if (null!= book) {
            return booksRepository.save(book);
        } else return null;
    }

    @Override
    public Book update(Book book) {
        if (null!= book) {
            return booksRepository.saveAndFlush(book);
        } else return null;
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
