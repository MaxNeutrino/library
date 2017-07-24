package project.neutrino.library.ui;

import project.neutrino.library.model.Book;
import project.neutrino.library.service.BookService;
import project.neutrino.library.ui.command.Command;
import project.neutrino.library.ui.command.CommandNotFoundExecutor;
import project.neutrino.library.ui.command.CommandStore;
import project.neutrino.library.util.annotation.FromXml;
import project.neutrino.library.util.annotation.StringValueInjector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CliController {

    @FromXml
    private String fewBooks;

    @FromXml
    private String canceled;

    @FromXml
    private String helloText;

    private CommandStore commandStore;

    private static CliController CONTROLLER;

    private BufferedReader reader;

    private CliController() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void printCanceledMessage() {
        printMessage(canceled);
    }

    public String getInputtedLine() throws IOException {
        return reader.readLine();
    }

    public Book printBooksAndWaitResponse(List<Book> books) throws IOException, IllegalArgumentException {
        printMessage(fewBooks);

        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            String message = String.format("%d. %s", i + 1, book.getName());
            printMessage(message);
        }

        int response = Integer.parseInt(getInputtedLine());
        if (response > books.size() || response <= 0)
            throw new IllegalArgumentException();

        return books.get(response - 1);
    }

    public void run() throws IOException {
        printMessage(helloText);
        String inputted = "";

        while (!inputted.equals("exit")) {
            inputted = reader.readLine().trim();
            int commandEndIndex = inputted.indexOf(" ");

            if (commandEndIndex > 0) {
                executeCommand(inputted, commandEndIndex);
            } else {
                executeCommand(inputted, null);
            }
        }
    }

    public void close() throws IOException {
        reader.close();
    }

    public static void initializeController(BookService service, StringValueInjector injector) throws Exception {
        if (CONTROLLER == null) {
            CONTROLLER = new CliController();
            injector.injectStringValues(CONTROLLER);
            CONTROLLER.commandStore = new CommandStore(service, injector);
        }
    }

    public static CliController getController() {
        return CONTROLLER;
    }

    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    public static void printError(String err) {
        System.err.println(err);
    }

    private void executeCommand(String commandName, String arg) {
        Command command = commandStore
                .getCommand(commandName)
                .orElse(new CommandNotFoundExecutor(commandName));

        command.execute(arg);
    }

    private void executeCommand(String inputted, int commandEndIndex) {
        String commandName = inputted.substring(0, commandEndIndex);
        String arg = inputted
                .substring(commandEndIndex)
                .trim();

        executeCommand(commandName, arg);
    }
}
