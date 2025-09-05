package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to mark task as done
 */
public class MarkCommand implements Command {

    /**
     * @inheritDoc
     * @throws UnknownInputException if task does not exist or no index is given
     * Marks task as done from TaskList based on index displayed on UI
     * Rewrites data file with updated TaskList and calls UI
     */
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {
        try {
            String numberStr = input.substring(5).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = tasks.get(number - 1);
            curr.markDone();

            ui.showMark(curr);

            storage.rewriteStorage(tasks);

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'mark' requires a number after");
        } catch (NullPointerException e) {
            throw new UnknownInputException("you can't mark a task that doesn't exist");
        }
    }
}
