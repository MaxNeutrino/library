package project.neutrino.library.ui.command.impl;

import project.neutrino.library.model.Book;
import project.neutrino.library.service.BookService;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.AbstractModifyCommand;
import project.neutrino.library.util.annotation.FromXml;

import java.io.IOException;

public class EditCommand extends AbstractModifyCommand {

    @FromXml
    private String editMessage;

    public EditCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    protected void modify(Book book) throws IOException {
        String oldName = book.getName();

        CliController.printMessage(editMessage);
        String newName = CliController.getController()
                .getInputtedLine()
                .trim();

        if (newName.length() == 0)
            throw new IllegalArgumentException();

        book.setName(newName);
        bookService.save(book);

        String message = String.format("book %s was updated to %s", oldName, newName);
        CliController.printMessage(message);
    }
}
