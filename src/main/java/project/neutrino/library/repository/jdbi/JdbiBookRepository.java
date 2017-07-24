package project.neutrino.library.repository.jdbi;

import org.skife.jdbi.v2.sqlobject.*;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import project.neutrino.library.model.Book;
import project.neutrino.library.repository.BookRepository;

import java.util.List;

@RegisterMapper(BookMapper.class)
public interface JdbiBookRepository extends BookRepository {

    @Override
    @SqlQuery("SELECT * FROM books WHERE id = :id")
    Book findById(@Bind("id") int id);

    @Override
    @SqlQuery("SELECT * FROM books WHERE name = :name")
    List<Book> findByName(@Bind("name") String name);

    @Override
    @SqlQuery("SELECT * FROM books WHERE name LIKE concat(:chars, '%') ORDER BY name")
    List<Book> findByNameContaining(@Bind("chars") String chars);

    @Override
    @SqlQuery("SELECT * FROM books ORDER BY name")
    List<Book> findAll();

    @Override
    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO books (name) VALUES (:name)")
    int create(@BindBean Book book);

    @Override
    @SqlUpdate("UPDATE books SET name = :name WHERE id = :id")
    void update(@BindBean Book book);

    @Override
    @SqlUpdate("DELETE FROM books WHERE id = :id")
    void delete(@Bind("id") int id);

    void close();
}
