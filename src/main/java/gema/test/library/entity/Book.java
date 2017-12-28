package gema.test.library.entity;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nameOfBook;
    private String author;

    public Book() {
    }

    public Book(String nameOfBook) {
        this.nameOfBook = nameOfBook;
        this.author="unknown";
    }

    public Book(String nameOfBook, String author) {
        this.nameOfBook = nameOfBook;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", nameOfBook='" + nameOfBook + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
