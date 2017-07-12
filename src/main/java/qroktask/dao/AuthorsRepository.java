package qroktask.dao;

import org.springframework.data.repository.CrudRepository;
import qroktask.models.Author;

public interface AuthorsRepository extends CrudRepository<Author, Integer>{
}
