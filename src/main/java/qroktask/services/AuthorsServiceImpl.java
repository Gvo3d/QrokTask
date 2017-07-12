package qroktask.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qroktask.dao.AuthorsRepository;
import qroktask.models.Author;

/**
 * Created by Gvozd on 12.07.2017.
 */
@Service
public class AuthorsServiceImpl implements AuthorsService {

    @Autowired
    AuthorsRepository authorsRepository;

    @Override
    public Iterable<Author> getAllAuthors() {
        return authorsRepository.findAll();
    }
}
