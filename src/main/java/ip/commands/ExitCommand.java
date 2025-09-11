package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to exit application
 */
public class ExitCommand implements Command {

    /**
     * @inheritDoc
     * @throws UnknownInputException if input contains other words
     * Causes the application to exit
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws UnknownInputException {
        if (!input.equals("bye")) {
            throw new UnknownInputException("Just enter 'bye' by itself");
        }

        return ui.showExit();
    }

    /**
     * Returns true to identify that it is an exit command
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
