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

    @Override
    public Author create(Author author) {
        if (null!= author) {
            return authorsRepository.save(author);
        } else return null;
    }

    @Override
    public Author update(Author author) {
        if (null!= author) {
            Optional<Author> fromDB = authorsRepository.getAuthorFetchAll(entityManager,author.getId());
            if (fromDB.isPresent()){
                Author authorFromDb = fromDB.get();
                authorFromDb.setSex(author.getSex());
                authorFromDb.setBirthDate(author.getBirthDate());
                authorFromDb.setRewards(author.getRewards());
                authorFromDb.setBooks(author.getBooks());
                authorFromDb.setFirstName(author.getFirstName());
                authorFromDb.setLastName(author.getLastName());
                return authorsRepository.saveAndFlush(authorFromDb);
            }
        }
        return null;
    }

    @Override
    public Boolean exists(Integer id) {
        if (null != id) {
            return authorsRepository.exists(id);
        } else return false;
    }

    @Override
    public Boolean delete(Integer id) {
        if (null != id) {
            if (authorsRepository.exists(id)) {
                authorsRepository.delete(id);
                return true;
            }
        }
        return false;
    }
}
