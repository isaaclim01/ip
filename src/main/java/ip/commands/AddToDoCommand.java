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
    private static final String PREFIX = "todo ";
    private static final int PREFIX_LENGTH = PREFIX.length();

    /**
     * @inheritDoc
     * @throws UnknownInputException if input is missing description
     * Adds ToDo Task into TaskList, appends task into data file and calls UI for response
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException {
        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("Your ToDo has to have a description!");
        }
        String taskDescription = input.substring(PREFIX_LENGTH).trim();

        ToDo addTask = new ToDo(taskDescription);

        storage.writeToStorage(addTask);
        tasks.addTask(addTask);

        return ui.showTaskInput(addTask);
    }
}
