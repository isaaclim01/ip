package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to delete task from TaskList based on index
 */
public class DeleteCommand implements Command {
    /**
     * @inheritDoc
     * @throws UnknownInputException if task does not exist or no index is given
     * Deletes task from TaskList based on index displayed on UI
     * Rewrites data file with updated TaskList and calls UI
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {
        try {
            int originalLength = tasks.size();
            String numberStr = input.substring(7).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = tasks.get(number - 1);
            tasks.remove(number - 1);

            assert tasks.size() == originalLength - 1: "Task not deleted!";

            storage.rewriteStorage(tasks);

            return ui.showDeleteCommand(curr, tasks.size());

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'delete' requires a number after");
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownInputException("you can't delete a task that doesn't exist");
        }
    }
}
