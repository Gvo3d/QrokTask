package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qroktask.dao.AuthorsRepository;
import qroktask.models.Author;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Gvozd on 12.07.2017.
 */
@Service
public class AuthorsServiceImpl implements AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Iterable<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }

    @Override
    @Transactional
    public Iterable<Author> getAllAuthorsFetchAll() {
        return authorsRepository.getAllAuthorsFetchAll(entityManager);
    }

    @Override
    public Author getOneAuthor(Integer id) {
        return authorsRepository.findOne(id);
    }

    @Override
    @Transactional
    public Author getOneAuthorFetchAll(Integer id) {
        Optional<Author> result = authorsRepository.getAuthorFetchAll(entityManager,id);
        return result.orElse(null);
    }
}
