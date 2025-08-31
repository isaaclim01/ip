package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.Ui;

public interface Command {

    //Executes command
    void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileNotFoundException, FileCorruptedException;

    //Checks if command is an exit command
    default boolean isExit() {
        return false;
    }
}
