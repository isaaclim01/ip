package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

public class ExitCommand implements Command {

    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws UnknownInputException {
        if (!input.equals("bye")) {
            throw new UnknownInputException("Just enter 'bye' by itself");
        }

        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
