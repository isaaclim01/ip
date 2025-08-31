package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

import java.io.FileNotFoundException;

/**
 * Command to mark task as not done
 */
public class UnmarkCommand implements Command {

    /**
     * @inheritDoc
     * @throws UnknownInputException if task does not exist or no index is given
     * Marks task as not done from TaskList based on index displayed on UI
     * Rewrites data file with updated TaskList and calls UI
     */
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
                UnknownInputException, FileNotFoundException, FileCorruptedException {
        try {
            String numberStr = input.substring(7).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = tasks.get(number - 1);
            curr.unmarkDone();

            ui.showUnmark(curr);

            storage.rewrite(tasks);

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'unmark' requires a number after");
        } catch (NullPointerException e) {
            throw new UnknownInputException("you can't unmark a task that doesn't exist");
        }
    }
}
