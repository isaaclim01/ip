package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.main.Storage;
import ip.tasks.Task;
import ip.ui.Ui;

import java.io.FileNotFoundException;

public class UnmarkCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage)
            throws UnknownInputException, FileNotFoundException, FileCorruptedException {
        try {
            String numberStr = input.substring(7).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.tasks.get(number - 1);
            curr.unmarkDone();

            System.out.println("I hope this empty box make you feel bad for procrastinating: ");
            System.out.print(curr.toString().indent(8));

            storage.rewrite();

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'unmark' requires a number after");
        } catch (NullPointerException e) {
            throw new UnknownInputException("you can't unmark a task that doesn't exist");
        }
    }
}
