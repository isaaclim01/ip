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

            String toWrite = String.format("\nT / 0 / %s", addTask.getDescription());

            data.write(toWrite);
            data.close();

            System.out.print("Let me write this down: \n" +
                    addTask.toString().indent(8));
        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }
}
