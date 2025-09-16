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
    private static final String PREFIX = "event ";
    private static final int PREFIX_LENGTH = PREFIX.length();
    private static final String PREFIX_TWO = "from  ";
    private static final int PREFIX_TWO_LENGTH = PREFIX_TWO.length();
    private static final String PREFIX_THREE = "to ";
    private static final int PREFIX_THREE_LENGTH = PREFIX_THREE.length();

    /**
     * @inheritDoc
     * Adds Event task into TaskList, appends task into data file and calls UI for response
     * @throws UnknownInputException if input is missing description, '/from' and '/to'
     *                               or valid startDate and endDate
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException {

        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        if (!input.contains("/" + PREFIX_TWO) || !input.contains("/" + PREFIX_THREE)) {
            throw new UnknownInputException("Your Event has to have a duration inputted with '/from' and '/to'");
        }

        String[] splitInputs = input.substring(PREFIX_LENGTH).split("/");
        String taskDescription = splitInputs[0].trim();

        if (taskDescription.isEmpty()) {
            throw new UnknownInputException("Your Event has to have a description!");
        }

        boolean hasNoStartDate = splitInputs[1].length() <= PREFIX_TWO_LENGTH;
        boolean startHasIncorrectFormat = !splitInputs[1].startsWith(PREFIX_TWO);
        boolean hasNoEndDate = splitInputs[2].length() <= PREFIX_THREE_LENGTH;
        boolean endHasIncorrectFormat = !splitInputs[2].startsWith(PREFIX_THREE);

        if (hasNoStartDate || startHasIncorrectFormat) {
            throw new UnknownInputException("Your Event has to have a start date inputted with '/from'");
        }

        if (hasNoEndDate || endHasIncorrectFormat) {
            throw new UnknownInputException("Your Event has to have a end date inputted with '/to'");
        }

        String startDate = splitInputs[1].substring(PREFIX_TWO_LENGTH).trim();
        String endDate = splitInputs[2].substring(PREFIX_THREE_LENGTH).trim();
        boolean startIsDate = DateValidator.isValidDate(startDate);
        boolean endIsDate = DateValidator.isValidDate(endDate);

        if (!startIsDate || !endIsDate) {
            throw new UnknownInputException("Your Event has to have start and end dates with format yyyy-mm-dd");
        }

        assert startIsDate && endIsDate : "Start date or end date is not valid";

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        Event addTask = new Event(taskDescription, start, end);

        storage.writeToStorage(addTask);
        tasks.addTask(addTask);
        assert tasks.contains(addTask): "Task not added";

        return ui.showTaskInput(addTask);

    }
}
