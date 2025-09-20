package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to snooze a task
 */
public class SnoozeCommand implements Command{
    private static final String PREFIX = "snooze ";
    private static final int PREFIX_LENGTH = PREFIX.length();

    /**
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException {

        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("'snooze' requires a number after!");
        }

        String[] splitInputs = input.substring(PREFIX_LENGTH).split("/");
        String numberString = splitInputs[0].trim();

        boolean isValidNumber = NumberValidator.isValidNumber(numberString);

        if (!isValidNumber) {
            throw new UnknownInputException("'snooze' requires a number after!");
        }

        int index = Integer.parseInt(numberString);

        if (index < 1 || index > tasks.size()) {
            throw new UnknownInputException("You can't snooze a task that doesn't exist!");
        }

        Task curr = tasks.get(index - 1);

        curr.snooze(splitInputs);
        storage.rewriteStorage(tasks);

        return ui.showTaskSnoozed(curr);
    }
}
