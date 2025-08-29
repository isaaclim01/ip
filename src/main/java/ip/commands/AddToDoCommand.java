package ip.commands;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.tasks.ToDo;

import java.io.FileWriter;
import java.io.IOException;

public class AddToDoCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException {
        if (input.length() == 4 || input.substring(5).trim().isEmpty()) {
            throw new UnknownInputException("Your ToDo has to have a description!");
        }
        ToDo addTask = new ToDo(input.substring(5).trim());

        try {
            FileWriter data = new FileWriter(Squiddy.DATA_PATHNAME, true);

            String dataString = addTask.toDataString() + "\n";

            data.write(dataString);
            data.close();
            Squiddy.tasks.add(addTask);

            System.out.print("Let me write this down: \n" +
                    addTask.toString().indent(8));
        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }
}
