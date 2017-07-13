package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qroktask.dao.BooksRepository;
import qroktask.models.Book;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    BooksRepository booksRepository;

    @Override
    public Iterable<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public Book getOneBook(Integer id) {
        return booksRepository.findOne(id);
    }
}
