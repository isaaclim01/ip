package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.tasks.ToDo;
import ip.ui.Ui;

public class AddToDoCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
                UnknownInputException, FileCorruptedException {
        if (input.length() == 4 || input.substring(5).trim().isEmpty()) {
            throw new UnknownInputException("Your ToDo has to have a description!");
        }
        ToDo addTask = new ToDo(input.substring(5).trim());

        storage.write(addTask);
        tasks.addTask(addTask);
        ui.showTaskInput(addTask);
    }
}
