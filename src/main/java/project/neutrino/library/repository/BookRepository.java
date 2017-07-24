package project.neutrino.library.repository;

import project.neutrino.library.model.Book;

import java.util.List;

public interface BookRepository {

    Book findById(int id);

    List<Book> findByName(String name);

    List<Book> findByNameContaining(String chars);

    List<Book> findAll();

    int create(Book book);

    void update(Book book);

    void delete(int id);
}
