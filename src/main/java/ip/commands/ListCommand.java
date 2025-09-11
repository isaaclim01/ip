package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to display TaskList
 */
public class ListCommand implements Command {

    /**
     * @inheritDoc
     * @throws UnknownInputException if other words are included or empty TaskList
     * Displays the current TaskList by iterating through the TaskList and printing each to output
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
                UnknownInputException, FileCorruptedException, FileNotFoundException {

        if (!input.equals("list")) {
            throw new UnknownInputException("Just enter 'list' by itself");
        }

        if (tasks.isEmpty()) {
            throw new UnknownInputException("your list is empty");
        }

        return ui.showListCommand(tasks);
    }
}
