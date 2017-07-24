package project.neutrino.library.service;

import org.junit.Before;
import project.neutrino.library.repository.BookRepository;
import project.neutrino.library.repository.ConnectionManager;
import project.neutrino.library.repository.RepositoryFactory;
import project.neutrino.library.repository.jdbi.JdbiBookRepository;
import project.neutrino.library.service.impl.BookServiceImpl;

public class AbstractServiceTest {

    protected BookService bookService;

    @Before
    public void setUp() {
        ConnectionManager.createAndConnect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        ConnectionManager connectionManager = ConnectionManager.getManager();
        connectionManager.executeSqlScript("db/initDB.sql");

        BookRepository bookRepository = RepositoryFactory.create(JdbiBookRepository.class);
        bookService = new BookServiceImpl(bookRepository);
    }
}
