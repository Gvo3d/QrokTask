package qroktask.services;

import qroktask.models.Author;

public interface AuthorsService {
    public Iterable<Author> getAllAuthors();
}
