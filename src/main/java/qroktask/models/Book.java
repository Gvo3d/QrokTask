package qroktask.models;

import lombok.Data;
import qroktask.models.support.Genre;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="Books")
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Book_id")
    private int id;

    @Column(name = "Title")
    private String title;

    @Column(name = "ISBN")
    private String isbn;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Genre")
    private Genre genre;
}
