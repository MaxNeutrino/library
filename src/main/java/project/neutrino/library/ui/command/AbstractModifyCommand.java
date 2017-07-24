package project.neutrino.library.ui.command;

import project.neutrino.library.model.Book;
import project.neutrino.library.service.BookService;
import project.neutrino.library.ui.CliController;

import java.io.IOException;
import java.util.List;

public abstract class AbstractModifyCommand implements Command {

    protected BookService bookService;

    public AbstractModifyCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void execute(String arg) {
        try {
            List<Book> books = bookService.findByNameContaining(arg);

            if (books.size() > 1) {
                Book book = CliController
                        .getController()
                        .printBooksAndWaitResponse(books);
                modify(book);

            } else if (books.size() == 1) {
                Book book = books.get(0);
                modify(book);

            } else {
                CliController.printError("book with such name not found");
            }
        } catch (Exception e) {
            CliController.getController()
                    .printCanceledMessage();
        }
    }

    protected abstract void modify(Book book) throws IOException;
}
