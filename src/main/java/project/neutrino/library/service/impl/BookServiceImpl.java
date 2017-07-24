package project.neutrino.library.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.neutrino.library.model.Book;
import project.neutrino.library.repository.BookRepository;
import project.neutrino.library.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    private Logger LOG = LogManager.getLogger(this.getClass());

    private BookRepository repository;

    public BookServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public Book find(int id) {
        LOG.info("find by id = ", id);
        return repository.findById(id);
    }

    @Override
    public List<Book> find(String name) {
        LOG.info("find by name = " + name);
        return repository.findByName(name);
    }

    @Override
    public List<Book> findByNameContaining(String chars) {
        LOG.info("findByNameContaining " + chars);
        return repository.findByNameContaining(chars);
    }

    @Override
    public List<Book> findAll() {
        LOG.info("findAll");
        return repository.findAll();
    }

    @Override
    public int save(Book book) {
        LOG.info("save or update book = ", book);
        if (book.getId() == null) {
            return repository.create(book);
        } else {
            repository.update(book);
            return book.getId();
        }
    }

    @Override
    public void delete(int id) {
        LOG.info("delete id = ", id);
        repository.delete(id);
    }
}
