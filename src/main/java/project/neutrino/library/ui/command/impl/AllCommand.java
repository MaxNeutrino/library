package project.neutrino.library.ui.command.impl;

import project.neutrino.library.model.Book;
import project.neutrino.library.service.BookService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;
import project.neutrino.library.util.annotation.FromXml;

import java.util.List;

public class AllCommand implements Command {

    @FromXml
    private String emptyLibrary;

    private BookService bookService;

    public AllCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void execute(String arg) {
        List<Book> books = bookService.findAll();

        if (books.isEmpty()) {
            CliController.printMessage(emptyLibrary);
        } else {
            CliController.printMessage("Our books:");
            books.forEach(book -> CliController.printMessage(
                    String.format("\t%s", book.getName())
            ));
        }
    }
}
