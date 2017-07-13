package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qroktask.dao.AuthorsRepository;
import qroktask.models.Author;
import qroktask.models.Author_;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 * Created by Gvozd on 12.07.2017.
 */
@Service
public class AuthorsServiceImpl implements AuthorsService {

    @Autowired
    AuthorsRepository authorsRepository;

    @Autowired
    EntityManager entityManager;

    @Override
    public Iterable<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }

    @Override
//    @Transactional
    public Iterable<Author> getAllAuthorsFetchAll() {
        CriteriaQuery<Author> query = entityManager.getCriteriaBuilder().createQuery(Author.class);
        Root<Author> root = query.from(Author.class);
        query.distinct(true);
        root.fetch(Author_.books, JoinType.LEFT);
        root.fetch(Author_.rewards, JoinType.LEFT);
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public Author getOneAuthor(Integer id) {
        return authorsRepository.findOne(id);
    }
}
