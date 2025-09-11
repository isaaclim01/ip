package ip.commands;

import ip.Squiddy;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to enable test mode which removes horizontal lines for output matching
 */
public class TestCommand implements Command {

    /**
     * @inheritDoc
     * @throws UnknownInputException if other words are in the input
     * Toggles test mode setting
     * Shows message on UI depending on test mode being enabled or disabled
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws UnknownInputException {
        if (!input.equals("test")) {
            throw new UnknownInputException("Just enter 'test' by itself");
        }

        Squiddy.setIsTestMode();
        return ui.showTestMode();
    }
}
