package ip.commands;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage, TaskList tasks) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {

        if (!input.equals("list")) {
            throw new UnknownInputException("Just enter 'list' by itself");
        }

        if (tasks.isEmpty()) {
            throw new UnknownInputException("your list is empty");
        }

        System.out.println("Let's see what you've got: ");

        int max = tasks.size();

        for (int i = 0; i < max; i++) {
            Task curr = tasks.get(i);
            ui.showListContent(curr, i + 1);
        }
    }
}
