package project.neutrino.library.ui.command.impl;

import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;
import project.neutrino.library.util.annotation.FromXml;

public class HelpCommand implements Command {

    @FromXml
    private String help;

    @Override
    public void execute(String arg) {
        CliController.printMessage(help);
    }
}
