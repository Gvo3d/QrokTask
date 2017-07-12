package qroktask.dao;

import org.springframework.data.repository.CrudRepository;
import qroktask.models.Book;

public interface BooksRepository extends CrudRepository<Book, Integer> {
}
