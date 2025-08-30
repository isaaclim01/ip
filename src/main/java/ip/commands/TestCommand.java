package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.Squiddy;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

public class TestCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws UnknownInputException {
        if (!input.equals("test")) {
            throw new UnknownInputException("Just enter 'test' by itself");
        }

        Squiddy.IS_TEST_MODE = !Squiddy.IS_TEST_MODE;
        ui.showTestMode();
    }
}
