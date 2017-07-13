package qroktask.models;

import qroktask.models.support.Genre;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.UUID;

@StaticMetamodel(Book.class)
public class Book_ {
    public static volatile SingularAttribute<Book, UUID> id;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, String> isbn;
    public static volatile SingularAttribute<Book, Genre> genre;
    public static volatile SetAttribute<Book, Author> authors;
}
