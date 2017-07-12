package qroktask.models;

import lombok.Data;
import qroktask.models.support.Genre;

import javax.persistence.*;
import java.util.Set;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "Genre")
    private Genre genre;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Author.class)
    @JoinTable(name = "books_to_authors",
            joinColumns = @JoinColumn(name = "Magazine_book_id"),
            inverseJoinColumns = @JoinColumn(name = "Magazine_author_id"))
    private Set<Author> authors;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                '}';
    }
}
