package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.main.Storage;
import ip.tasks.Deadline;
import ip.ui.Ui;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddDeadlineCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage) throws
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

        if (isDate) {
            LocalDate date = LocalDate.parse(dueDate);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yy");
            dueDate = date.format(formatter);
        }

        Deadline addTask = new Deadline(splitInput[0].trim(), dueDate);

        storage.write(addTask);
        Squiddy.tasks.add(addTask);
        ui.showTaskInput(addTask);
    }
}
