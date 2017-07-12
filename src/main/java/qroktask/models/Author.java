package qroktask.models;

import lombok.Data;
import qroktask.models.support.Sex;

import javax.persistence.*;
import java.util.Date;

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

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "Sex")
    private Sex sex;

    @Column(name = "Birth_date")
    private Date birthDate;
}
