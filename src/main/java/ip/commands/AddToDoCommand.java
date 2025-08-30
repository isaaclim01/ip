package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.main.Storage;
import ip.tasks.ToDo;
import ip.ui.Ui;

import java.io.FileWriter;
import java.io.IOException;

public class AddToDoCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage) throws
            UnknownInputException, FileCorruptedException {
        if (input.length() == 4 || input.substring(5).trim().isEmpty()) {
            throw new UnknownInputException("Your ToDo has to have a description!");
        }
        ToDo addTask = new ToDo(input.substring(5).trim());

        storage.write(addTask);
        Squiddy.tasks.add(addTask);
        ui.showTaskInput(addTask);
    }
}
