import java.io.FileWriter;
import java.io.IOException;

public class AddDeadlineCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException, FileCorruptedException {

        if (input.length() == 8) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        if (!input.contains("/by")) {
            throw new UnknownInputException("Your Deadline has to have a due date inputted with '/by'");
        }

        String[] splitInput = input.substring(9).split("/");

        if (splitInput[0].trim().isEmpty()) {
            throw new UnknownInputException("Your Deadline has to have a description!");
        }

        try {
            splitInput[1] = splitInput[1].substring(3);
        } catch (StringIndexOutOfBoundsException e) {
            throw new UnknownInputException("Add the deadline after '/by'");
        }

        Deadline addTask = new Deadline(splitInput[0].trim(), splitInput[1].trim());

        try {
            FileWriter data = new FileWriter(Squiddy.DATA_PATHNAME, true);

            String toWrite = String.format("\nD / 0 / %s / %s", addTask.getDescription(),
                    addTask.getDueDate());

            data.write(toWrite);
            data.close();

            System.out.print("Let me write this down: \n" +
                    addTask.toString().indent(8));
        } catch (IOException e) {
            throw new FileCorruptedException(e.getMessage());
        }
    }
}
