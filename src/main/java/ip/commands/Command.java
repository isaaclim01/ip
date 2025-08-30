package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Storage;
import ip.ui.Ui;

import java.io.FileNotFoundException;

public interface Command {

    //Executes command
    void execute(String input, Ui ui, Storage storage) throws
            UnknownInputException, FileNotFoundException, FileCorruptedException;

    //Checks if command is an exit command
    default boolean isExit() {
        return false;
    }
}
