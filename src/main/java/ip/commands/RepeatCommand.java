package ip.commands;

import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

public class RepeatCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) {
        ui.showRepeat(input);
    }
}
