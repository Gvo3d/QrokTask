package qroktask.dao;

import org.springframework.data.repository.CrudRepository;
import qroktask.models.Book;
import qroktask.models.Book_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.Optional;

public interface BooksRepository extends CrudRepository<Book, Integer> {
    default Iterable<Book> getAllBooksFetchAll(EntityManager entityManager) {
        if (null!= entityManager) {
            CriteriaQuery<Book> query = entityManager.getCriteriaBuilder().createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.distinct(true);
            root.fetch(Book_.authors, JoinType.LEFT);
            return entityManager.createQuery(query).getResultList();
        } else return Collections.emptyList();
    }

    default Optional<Book> getBookFetchAll(EntityManager entityManager, Integer id) {
        if (null!= entityManager) {
            Book result;
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            query.distinct(true);
            root.fetch(Book_.authors, JoinType.LEFT);
            query.where(builder.equal(root.get(Book_.id), id)).distinct(true);
            result = entityManager.createQuery(query).getSingleResult();
            return Optional.of(result);
        } else return Optional.empty();
    }
}
