package project.neutrino.library.ui.command.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import project.neutrino.library.ui.CliController;
import project.neutrino.library.ui.command.Command;

import java.io.IOException;

public class ExitCommand implements Command {

    private Logger LOG = LogManager.getLogger(this);

    @Override
    public void execute(String arg) {
        try {
            CliController.getController().close();
        } catch (IOException e) {
            LOG.error("exception by closing resources", e);
        }

        CliController.printMessage("Bye");
        System.exit(0);
    }
}
