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

    /**
     * @inheritDoc
     * @throws UnknownInputException if input is missing description, '/by' or valid dueDate
     * Adds Deadline task into TaskList, appends task into data file and calls UI for response
     */
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
                UnknownInputException, FileCorruptedException {

        if (input.length() == 8) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        if (!input.contains("/by")) {
            throw new UnknownInputException("Your Deadline has to have a due date inputted with '/by'");
        }

        String[] splitInputs = input.substring(9).split("/");

        if (splitInputs[0].trim().isEmpty()) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        try {
            splitInputs[1] = splitInputs[1].substring(3);
        } catch (StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("Add the deadline after '/by'");
        }

        String dueDate = splitInputs[1].trim();
        boolean isDate = DateValidator.isValidDate(dueDate);

        if (!isDate) {
            throw new UnknownInputException("Your Deadline has to have a due date in the format yyyy-mm-dd");
        }

        LocalDate date = LocalDate.parse(dueDate);
        Deadline addTask = new Deadline(splitInputs[0].trim(), date);

        storage.writeToStorage(addTask);
        tasks.addTask(addTask);
        ui.showTaskInput(addTask);
    }
}
