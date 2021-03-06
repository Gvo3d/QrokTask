package qroktask.models;

import com.fasterxml.jackson.annotation.JsonView;
import qroktask.models.support.Validatable;
import qroktask.support.JacksonMappingMarker;
import qroktask.models.enums.Sex;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "Authors")
//@NamedEntityGraph(name = "Authors.fetchAll",
//        attributeNodes = {@NamedAttributeNode("books"), @NamedAttributeNode("rewards")})
public class Author implements Validatable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "Author_id")
    @JsonView(JacksonMappingMarker.Lower.class)
    private int id;

    @Column(name = "First_name")
    @JsonView(JacksonMappingMarker.Lower.class)
    private String firstName;

    @Column(name = "Last_name")
    @JsonView(JacksonMappingMarker.Lower.class)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "Sex")
    @JsonView(JacksonMappingMarker.Lower.class)
    private Sex sex;

    @Column(name = "Birth_date")
    @JsonView(JacksonMappingMarker.Lower.class)
    private Date birthDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Book.class)
    @JoinTable(name = "books_to_authors",
            joinColumns = @JoinColumn(name = "Magazine_author_id"),
            inverseJoinColumns = @JoinColumn(name = "Magazine_book_id"))
    @JsonView(JacksonMappingMarker.Higher.class)
    private Set<Book> books = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Reward.class)
    @JoinTable(name = "authors_to_rewards",
            joinColumns = @JoinColumn(name = "Magazine_author_id"),
            inverseJoinColumns = @JoinColumn(name = "Magazine_reward_id"))
    @JsonView(JacksonMappingMarker.Higher.class)
    private Set<Reward> rewards = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book){
        this.books.add(book);
    }

    public void removeBook(Book book){
        this.books.remove(book);
    }

    public Set<Reward> getRewards() {
        return rewards;
    }

    public void addReward(Reward reward) {
        this.rewards.add(reward);
    }

    public void removeReward(Reward reward){
        this.rewards.remove(reward);
    }

    public void setRewards(Set<Reward> rewards) {
        this.rewards = rewards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (id != author.id) return false;
        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;
        if (lastName != null ? !lastName.equals(author.lastName) : author.lastName != null) return false;
        if (sex != author.sex) return false;
        return birthDate != null ? birthDate.equals(author.birthDate) : author.birthDate == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        return result;
    }

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

    public String toStringWithAll() {
        return "Author{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", sex=" + sex +
                ", birthDate=" + birthDate +
                ", books=" + books +
                ", rewards=" + rewards +
                '}';
    }

    @Override
    public boolean validate() {
        return (null!=firstName && null!=lastName && sex!=null && birthDate!=null);
    }
}
