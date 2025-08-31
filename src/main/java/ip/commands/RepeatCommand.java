package ip.commands;

import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Default command to fallback when input does not match any other command
 */
public class RepeatCommand implements Command {

    /**
     * @inheritDoc
     * Calls UI to display message for unknown command
     */
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) {
        ui.showRepeat(input);
    }
}
