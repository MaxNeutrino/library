package project.neutrino.library.ui.command.impl;

import project.neutrino.library.model.Book;
import project.neutrino.library.service.BookService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.AbstractModifyCommand;

import java.io.IOException;

public class RemoveCommand extends AbstractModifyCommand {

    public RemoveCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    protected void modify(Book book) throws IOException {
        bookService.delete(book.getId());
        String message = String.format("Book %s was removed", book.getName());
        CliController.printMessage(message);
    }
}
