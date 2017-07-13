package qroktask.services;

import qroktask.models.Author;

public interface AuthorsService {
    Iterable<Author> getAllAuthors();
    Iterable<Author> getAllAuthorsFetchAll();
    Author getOneAuthor(Integer id);
    Author getOneAuthorFetchAll(Integer id);
    Author createOrUpdateAuthor(Author author);
    Boolean exists(Integer id);
    Boolean delete(Integer id);
}
