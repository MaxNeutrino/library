package project.neutrino.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.neutrino.library.repository.BookRepository;
import project.neutrino.library.repository.RepositoryFactory;
import project.neutrino.library.repository.jdbi.JdbiBookRepository;
import project.neutrino.library.service.BookService;
import project.neutrino.library.service.impl.BookServiceImpl;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.util.annotation.StringValueInjector;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please wait");
        final Logger LOG = LogManager.getLogger();
        try {
            BookRepository repository = RepositoryFactory.create(JdbiBookRepository.class);
            BookService service = new BookServiceImpl(repository);

            InputStream stringsXml = Main.class.getClassLoader()
                    .getResourceAsStream("strings.xml");

            StringValueInjector injector = new StringValueInjector(stringsXml);

            CliController.initializeController(service, injector);
            CliController controller = CliController.getController();
            controller.run();

        } catch (Exception e) {
            LOG.error("initialization exception", e);
            String logFilePath = System.getProperty("user.home") + "/.library.log";
            System.err.println(
                    String.format("Program finished with error, see file %s for details", logFilePath));
            System.exit(1);
        }
    }
}
