package ip.commands;

import java.time.LocalDate;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Deadline;
import ip.tasks.TaskList;
import ip.ui.Ui;

public class AddDeadlineCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException {

        if (input.length() == 8) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        if (!input.contains("/by")) {
            throw new UnknownInputException("Your Deadline has to have a due date inputted with '/by'");
        }

        String[] splitInput = input.substring(9).split("/");

        if (splitInput[0].trim().isEmpty()) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        try {
            splitInput[1] = splitInput[1].substring(3);
        } catch (StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("Add the deadline after '/by'");
        }

        String dueDate = splitInput[1].trim();
        boolean isDate = DateValidator.isValid(dueDate);

        if (!isDate) {
            throw new UnknownInputException("Your Deadline has to have a due date in the format yyyy-mm-dd");
        }

        LocalDate date = LocalDate.parse(dueDate);
        Deadline addTask = new Deadline(splitInput[0].trim(), date);

        storage.write(addTask);
        tasks.addTask(addTask);
        ui.showTaskInput(addTask);
    }
}
