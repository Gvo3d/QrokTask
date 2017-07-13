package qroktask.models;

import com.fasterxml.jackson.annotation.JsonView;
import qroktask.support.JacksonMappingMarker;
import qroktask.models.support.Genre;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="Books")
public class Book {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Book_id")
    @JsonView(JacksonMappingMarker.List.class)
    private int id;

    @Column(name = "Title")
    @JsonView(JacksonMappingMarker.List.class)
    private String title;

    @Column(name = "ISBN")
    @JsonView(JacksonMappingMarker.List.class)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "Genre")
    @JsonView(JacksonMappingMarker.List.class)
    private Genre genre;

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = Author.class)
    @JoinTable(name = "books_to_authors",
            joinColumns = @JoinColumn(name = "Magazine_book_id"),
            inverseJoinColumns = @JoinColumn(name = "Magazine_author_id"))
    @JsonView(JacksonMappingMarker.Data.class)
    private Set<Author> authors;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (getId() != book.getId()) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getIsbn() != null ? !getIsbn().equals(book.getIsbn()) : book.getIsbn() != null) return false;
        return getGenre() == book.getGenre();
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                '}';
    }


    public String toStringWithAll() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre=" + genre +
                ", authors=" + authors +
                '}';
    }
}
