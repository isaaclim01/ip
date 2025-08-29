package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.main.FileManager;
import ip.main.Squiddy;
import ip.tasks.Task;

public class DeleteCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException {
        try {
            String numberStr = input.substring(7).trim();
            int number = Integer.parseInt(numberStr);
            Task curr = Squiddy.tasks.get(number - 1);
            Squiddy.tasks.remove(number - 1);

            System.out.println("Removing this task: ");
            System.out.print(curr.toString().indent(8));
            System.out.println(String.format("You have %d tasks recorded", Squiddy.tasks.size()));

            Squiddy.manager.rewrite();

        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("'delete' requires a number after");
        } catch (IndexOutOfBoundsException e) {
            throw new UnknownInputException("you can't delete a task that doesn't exist");
        }
    }
}
