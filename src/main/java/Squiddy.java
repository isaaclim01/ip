import java.util.ArrayList;
import java.util.Scanner;

public class Squiddy {

    public static ArrayList<Task> list = new ArrayList<>();
    public static CommandMapper mapper = new CommandMapper();
    public static boolean TEST_MODE = false;

    public static void printHorizontalLine() {
        if (!TEST_MODE) {
            System.out.println("—".repeat(50));
        }
    }

    public static void main(String[] args) {

        Scanner myScanner = new Scanner(System.in);

        if (FileManager.checkFolder()) {
            System.out.println("Folder loaded");
        } else {
            FileManager.createFolder();
        }

        if (FileManager.checkFile()) {
            System.out.println("Data loaded");
        } else {
            FileManager.createFile();
        }


        System.out.println("-".repeat(50));
        System.out.println("I'm Squiddy, forced to be text in your terminal. " +
                "\nType a task and I'll decide if I want to help you remember it");
        System.out.println("-".repeat(50));

        String input = myScanner.nextLine().trim();
        System.out.println("-".repeat(50));

        while (!input.equalsIgnoreCase("bye")) {
            try {
                Command command = mapper.getCommand(input.toLowerCase());
                command.execute(input);

                printHorizontalLine();
                input = myScanner.nextLine().trim().toLowerCase();
                printHorizontalLine();
            } catch (UnknownInputException e) {
                System.out.println("You can't do that: " + e.getMessage());
                printHorizontalLine();
                input = myScanner.nextLine().trim().toLowerCase();
                printHorizontalLine();
            }
        }

        System.out.println("Bye. Please don't bother me again.");
        printHorizontalLine();
    }
}
