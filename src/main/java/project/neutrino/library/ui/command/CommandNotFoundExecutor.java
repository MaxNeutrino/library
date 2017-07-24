package project.neutrino.library.ui.command;

import project.neutrino.library.ui.CliController;

public class CommandNotFoundExecutor implements Command {

    private String wrongCommandName;

    public CommandNotFoundExecutor(String wrongCommandName) {
        this.wrongCommandName = wrongCommandName;
    }

    @Override
    public void execute(String arg) {
        CliController.printError(
                String.format("Command %s not found. Write 'help' to see commands list", wrongCommandName)
        );
    }
}
