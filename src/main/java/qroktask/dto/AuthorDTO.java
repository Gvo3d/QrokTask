package qroktask.dto;

import qroktask.models.Author;
import qroktask.models.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AuthorDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<String> books = new ArrayList<>();

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public AuthorDTO(Author author) {
        if (null != author) {
            this.firstName = author.getFirstName();
            this.lastName = author.getLastName();
            Calendar present = Calendar.getInstance();
            Calendar past = Calendar.getInstance();
            past.setTime(author.getBirthDate());
            int years = 0;
            while (past.before(present)) {
                past.add(Calendar.YEAR, 1);
                if (past.before(present)) {
                    years++;
                }
            }
            this.age = years;
            for (Book book : author.getBooks()) {
                books.add(book.getTitle());
            }
        }
    }

    public boolean validate(){
        return (null!=firstName && null!=lastName && age!=0 && null!=books);
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", books=" + books +
                '}';
    }
}

