package qroktask.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import qroktask.models.Author;

public interface AuthorsRepository extends JpaRepository<Author, Integer> {

}
