package ip.commands;

import java.time.LocalDate;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Event;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to add Event task to task list when given one as input
 */
public class AddEventCommand implements Command {

    /**
     * @inheritDoc
     * @throws UnknownInputException if input is missing description, '/from' and '/to'
     * or valid startDate and endDate
     * Adds Event task into TaskList, appends task into data file and calls UI for response
     */
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
                UnknownInputException, FileCorruptedException {

        if (input.length() == 5) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        if (!input.contains("/from") || !input.contains("/to")) {
            throw new UnknownInputException("Your Event has to have a duration inputted with '/from' and '/to'");
        }

        String[] splitInput = input.substring(6).split("/");

        if (splitInput[0].trim().isEmpty()) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        if (splitInput[1].startsWith("from ")) {
            splitInput[1] = splitInput[1].substring(5);
            if (splitInput[1].isEmpty()) {
                throw new UnknownInputException("Your Event has to have a start date inputted with '/from'");
            }
        } else {
            throw new UnknownInputException("Your Event has to have a start date inputted with '/from'");
        }

        if (splitInput[2].startsWith("to ")) {
            splitInput[2] = splitInput[2].substring(3);
        } else {
            throw new UnknownInputException("Your Event has to have a end date inputted with '/to'");
        }

        String startDate = splitInput[1].trim();
        String endDate = splitInput[2].trim();
        boolean startIsDate = DateValidator.isValidDate(startDate);
        boolean endIsDate = DateValidator.isValidDate(endDate);

        if (!startIsDate || !endIsDate) {
            throw new UnknownInputException("Your Event has to have start and end dates with format yyyy-mm-dd");
        }

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        Event addTask = new Event(splitInput[0].trim(), start, end);

        storage.writeToStorage(addTask);
        tasks.addTask(addTask);
        ui.showTaskInput(addTask);

    }
}
