package qroktask.models;

import lombok.Data;
import qroktask.models.support.Sex;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name="Authors")
public class Author {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Author_id")
    private int id;

    @Column(name = "First_name")
    private String firstName;

    @Column(name = "Last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Sex")
    private Sex sex;

    @Column(name = "Birth_date")
    private Date birthDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Book.class)
    @JoinTable(name = "books_to_authors",
            joinColumns = @JoinColumn(name = "Magazine_author_id"),
            inverseJoinColumns = @JoinColumn(name = "Magazine_book_id"))
    private Set<Book> books;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Reward.class)
    @JoinTable(name = "authors_to_rewards",
            joinColumns = @JoinColumn(name = "Magazine_author_id"),
            inverseJoinColumns = @JoinColumn(name = "Magazine_reward_id"))
    private Set<Reward> rewards;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                '}';
    }
}
