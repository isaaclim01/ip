import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ListCommand implements Command {
    @Override
    public void execute(String input) throws UnknownInputException, FileNotFoundException, FileCorruptedException {

        if (!input.equals("list")) {
            throw new UnknownInputException("Just enter 'list' by itself");
        }

        File data = new File(Squiddy.DATA_PATHNAME);

        Scanner s = new Scanner(data);

        if (!s.hasNext()) {
            throw new UnknownInputException("your list is empty");
        }

        System.out.println("Let's see what you've got: ");

        int count = 1;

        while (s.hasNext()) {
            try {
                String curr = s.nextLine();
                String[] splitCurr = curr.split("/");
                Task task;

                switch (splitCurr[0].trim()) {
                case "T":
                    task = new ToDo(splitCurr[2].trim());
                    break;
                case "D":
                    task = new Deadline(splitCurr[2].trim(), splitCurr[3].trim());
                    break;
                case "E":
                    task = new Event(splitCurr[2].trim(), splitCurr[3].trim(), splitCurr[4].trim());
                    break;
                default:
                    String err = String.format("Task %d does not have valid type", count);
                    throw new FileCorruptedException(err);
                }

                if (splitCurr[1].trim().equals("1")) {
                    task.markDone();
                }

                String taskString = String.format("%d. %s \n", count++, task).indent(8);
                System.out.printf(taskString);

            } catch (ArrayIndexOutOfBoundsException e) {
                throw new FileCorruptedException(e.getMessage());
            }
        }
    }
}
