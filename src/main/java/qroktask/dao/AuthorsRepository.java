package qroktask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qroktask.models.Author;
import qroktask.models.Author_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.Optional;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {
    default Iterable<Author> getAllAuthorsFetchAll(EntityManager entityManager) {
        if (null!= entityManager) {
            CriteriaQuery<Author> query = entityManager.getCriteriaBuilder().createQuery(Author.class);
            Root<Author> root = query.from(Author.class);
            query.distinct(true);
            root.fetch(Author_.books, JoinType.LEFT);
            root.fetch(Author_.rewards, JoinType.LEFT);
            return entityManager.createQuery(query).getResultList();
        } else return Collections.emptyList();
    }

    default Optional<Author> getAuthorFetchAll(EntityManager entityManager, Integer id) {
        if (null!= entityManager) {
            Author result;
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Author> query = builder.createQuery(Author.class);
            Root<Author> root = query.from(Author.class);
            query.distinct(true);
            root.fetch(Author_.books, JoinType.LEFT);
            root.fetch(Author_.rewards, JoinType.LEFT);
            query.where(builder.equal(root.get(Author_.id), id)).distinct(true);
            result = entityManager.createQuery(query).getSingleResult();
            return Optional.of(result);
        } else return Optional.empty();
    }
}
