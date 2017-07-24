package project.neutrino.library.ui.command.impl;

import project.neutrino.library.model.Book;
import project.neutrino.library.service.BookService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;

public class AddCommand implements Command {

    private BookService bookService;

    public AddCommand(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void execute(String arg) {
        Book book = new Book(arg);
        bookService.save(book);
        String message = String.format("book %s was added", book.getName());
        CliController.printMessage(message);
    }
}
