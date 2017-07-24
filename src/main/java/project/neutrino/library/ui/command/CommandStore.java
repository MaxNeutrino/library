package project.neutrino.library.ui.command;

import project.neutrino.library.service.BookService;
import project.neutrino.library.ui.command.impl.*;
import project.neutrino.library.util.annotation.StringValueInjector;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandStore {

    private Map<String, Command> commands;

    private BookService service;

    private StringValueInjector injector;

    public CommandStore(BookService service, StringValueInjector injector) {
        this.service = service;
        this.injector = injector;
        commands = createCommands();
    }

    public Optional<Command> getCommand(String commandName) {
        return Optional.ofNullable(commands.get(commandName));
    }

    private Map<String, Command> createCommands() {
        Map<String, Command> commands = new HashMap<>();

        commands.put("add", new AddCommand(service));
        commands.put("all", new AllCommand(service));
        commands.put("edit", new EditCommand(service));
        commands.put("exit", new ExitCommand());
        commands.put("help", new HelpCommand());
        commands.put("remove", new RemoveCommand(service));

        commands.forEach((key, value) -> injectString(value));

        return Collections.unmodifiableMap(commands);
    }

    private Command injectString(Command command) {
        try {
            injector.injectStringValues(command);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return command;
    }
}
