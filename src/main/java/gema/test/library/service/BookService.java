package gema.test.library.service;

import gema.test.library.entity.Book;

import java.util.List;

public interface BookService {

    void save();
    List<Book>findAll();
    List<Book>findByBookName(String nameOfBook);
    Book findOne(int id);
    void editBook();
    void deleteBook();

}
