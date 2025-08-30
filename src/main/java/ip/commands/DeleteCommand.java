package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.main.Storage;
import ip.tasks.Task;
import ip.ui.Ui;

import java.io.FileNotFoundException;

public class DeleteCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage) throws
            UnknownInputException, FileCorruptedException, FileNotFoundException {
        try {
            String numberStr = input.substring(7).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.tasks.get(number - 1);
            Squiddy.tasks.remove(number - 1);

            System.out.println("Removing this task: ");
            System.out.print(curr.toString().indent(8));
            System.out.println(String.format("You have %d tasks recorded", Squiddy.tasks.size()));

            storage.rewrite();

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'delete' requires a number after");
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownInputException("you can't delete a task that doesn't exist");
        }
    }
}
