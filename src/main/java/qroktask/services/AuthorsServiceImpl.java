package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qroktask.dao.AuthorsRepository;
import qroktask.models.Author;

import javax.transaction.Transactional;

/**
 * Created by Gvozd on 12.07.2017.
 */
@Service
public class AuthorsServiceImpl implements AuthorsService {

    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public Iterable<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }

    @Override
    @Transactional
    public Iterable<Author> getAllAuthorsFetchAll() {
        Iterable<Author> iterable = authorsRepository.findAll();
        for (Author author:iterable){
            author.getBooks().size();
            author.getRewards().size();
        }
        return iterable;
    }

    @Override
    public Author getOneAuthor(Integer id) {
        return authorsRepository.findOne(id);
    }

    @Override
    @Transactional
    public Author getOneAuthorFetchAll(Integer id) {
        Author author = authorsRepository.findOne(id);
        author.getBooks().size();
        author.getRewards().size();
        return author;
    }

    @Override
    public Author create(Author author) {
        if (null!= author) {
            return authorsRepository.save(author);
        } else return null;
    }

    @Override
    @Transactional
    public Author update(Author author) {
        if (null!= author) {
            Author fromDB = getOneAuthorFetchAll(author.getId());
            if (null!=fromDB){
                fromDB.setSex(author.getSex());
                fromDB.setBirthDate(author.getBirthDate());
                fromDB.setRewards(author.getRewards());
                fromDB.setBooks(author.getBooks());
                fromDB.setFirstName(author.getFirstName());
                fromDB.setLastName(author.getLastName());
                return authorsRepository.saveAndFlush(fromDB);
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
