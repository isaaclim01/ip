package ip.commands;

import java.time.LocalDate;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Deadline;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to add deadline task to task list when given one as input
 */
public class AddDeadlineCommand implements Command {
    private static final String PREFIX = "deadline ";
    private static final int PREFIX_LENGTH = PREFIX.length();
    private static final String PREFIX_TWO = "by ";
    private static final int PREFIX_TWO_LENGTH = PREFIX_TWO.length();

    /**
     * @inheritDoc
     * @throws UnknownInputException if input is missing description, '/by' or valid dueDate
     * Adds Deadline task into TaskList, appends task into data file and calls UI for response
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
                UnknownInputException, FileCorruptedException {

        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        if (!input.contains("/" + PREFIX_TWO)) {
            throw new UnknownInputException("Your Deadline has to have a due date inputted with '/by'");
        }

        String[] splitInputs = input.substring(PREFIX_LENGTH).split("/");
        String taskDescription = splitInputs[0].trim();

        if (taskDescription.isEmpty()) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        boolean hasNoDueDate = splitInputs[1].length() <= PREFIX_TWO_LENGTH;
        boolean hasIncorrectFormat = !splitInputs[1].startsWith(PREFIX_TWO);

        if (hasNoDueDate || hasIncorrectFormat) {
            throw new UnknownInputException("Your Deadline has to have a due date inputted with '/by'");
        }

        String dueDate = splitInputs[1].substring(PREFIX_TWO_LENGTH).trim();
        boolean isDate = DateValidator.isValidDate(dueDate);

        if (!isDate) {
            throw new UnknownInputException("Your Deadline has to have a due date in the format yyyy-mm-dd");
        }

        LocalDate date = LocalDate.parse(dueDate);
        Deadline addTask = new Deadline(taskDescription, date);

        storage.writeToStorage(addTask);
        tasks.addTask(addTask);
        assert tasks.contains(addTask) : "Task not added";

        return ui.showTaskInput(addTask);
    }
}
