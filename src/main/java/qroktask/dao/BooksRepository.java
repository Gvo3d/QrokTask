package qroktask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qroktask.models.Book;

public interface BooksRepository extends JpaRepository<Book, Integer> {
}
