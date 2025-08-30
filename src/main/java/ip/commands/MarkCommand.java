package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.main.Storage;
import ip.tasks.Task;
import ip.ui.Ui;

import java.io.FileNotFoundException;

public class MarkCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {
        try {
            String numberStr = input.substring(5).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.tasks.get(number - 1);
            curr.markDone();

            System.out.println("OK, you've completed this: ");
            System.out.print(curr.toString().indent(8));

            storage.rewrite();

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'mark' requires a number after");
        } catch (NullPointerException e) {
            throw new UnknownInputException("you can't mark a task that doesn't exist");
        }
    }
}
