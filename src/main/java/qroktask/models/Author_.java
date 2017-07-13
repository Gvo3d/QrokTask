package qroktask.models;

import qroktask.models.enums.Sex;

import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.util.Date;
import java.util.UUID;

@StaticMetamodel(Author.class)
public class Author_ {
    public static volatile SingularAttribute<Author, UUID> id;
    public static volatile SingularAttribute<Author, String> firstName;
    public static volatile SingularAttribute<Author, String> lastName;
    public static volatile SingularAttribute<Author, Sex> sex;
    public static volatile SingularAttribute<Author, Date> birthDate;
    public static volatile SetAttribute<Author, Reward> rewards;
    public static volatile SetAttribute<Author, Book> books;
}
