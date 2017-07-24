package project.neutrino.library.service;

import project.neutrino.library.model.Book;

import java.util.List;

public interface BookService {

    Book find(int id);

    List<Book> find(String name);

    List<Book> findByNameContaining(String chars);

    List<Book> findAll();

    int save(Book book);

    void delete(int id);
}
