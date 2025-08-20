import java.util.Scanner;

public class Squiddy {

    public static Task[] list = new Task[100];
    public static int counter = 0;
    public static CommandMapper mapper = new CommandMapper();

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("—".repeat(50));
        System.out.println("I'm Squiddy, forced to be text in your terminal. " +
                "\nType a task and I'll decide if I want to help you remember it");
        System.out.println("—".repeat(50));

        String input = myScanner.nextLine().trim();
        System.out.println("—".repeat(50));

        while (!input.equalsIgnoreCase("bye")) {

            Command command = mapper.getCommand(input.toLowerCase());
            command.execute(input);

            System.out.println("—".repeat(50));
            input = myScanner.nextLine().trim().toLowerCase();
            System.out.println("—".repeat(50));
        }

        System.out.println("Bye. Please don't bother me again.");
        System.out.println("—".repeat(50));
    }
}
