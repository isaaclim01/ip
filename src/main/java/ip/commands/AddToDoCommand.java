package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.tasks.ToDo;
import ip.ui.Ui;

/**
 * Command to add Todo task to task list when given one as input
 */
public class AddToDoCommand implements Command {

    /**
     * @inheritDoc
     * @throws UnknownInputException if input is missing description
     * Adds ToDo Task into TaskList, appends task into data file and calls UI for response
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException {
        if (input.length() == 4 || input.substring(5).trim().isEmpty()) {
            throw new UnknownInputException("Your ToDo has to have a description!");
        }
        ToDo addTask = new ToDo(input.substring(5).trim());

        storage.write(addTask);
        tasks.addTask(addTask);
        return ui.showTaskInput(addTask);
    }
}
