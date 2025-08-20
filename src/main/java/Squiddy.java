import java.util.Scanner;

public class Squiddy {

    public static Task[] list = new Task[100];
    public static int counter = 0;

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("—".repeat(50));
        System.out.println("I'm Squiddy, forced to be text in your terminal. " +
                "\nType a task and I'll decide if I want to help you remember it");
        System.out.println("—".repeat(50));

        String input = myScanner.nextLine().trim().toLowerCase();
        System.out.println("—".repeat(50));

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                System.out.println("Let's see what you've got: ");

                for (int i = 0; i < counter; i++) {
                    Task curr = list[i];
                    System.out.printf("%d.[%s] %s \n", i + 1,
                            curr.getStatusIcon(),
                            curr.getDescription());
                }

            } else if (input.startsWith("mark ")) {
                try {
                    String numberStr = input.substring(5).trim();
                    int number = Integer.parseInt(numberStr);
                    Task curr = list[number - 1];
                    curr.markDone();

                    System.out.println("OK, you've completed this: ");
                    System.out.printf("%d.[%s] %s \n", number,
                            curr.getStatusIcon(),
                            curr.getDescription());

                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    System.out.println("Don't start your task with the word 'mark'. Please.");
                }

            } else if (input.startsWith("unmark ")) {
                try {
                    String numberStr = input.substring(7).trim();
                    int number = Integer.parseInt(numberStr);
                    Task curr = list[number - 1];
                    curr.unmarkDone();

                    System.out.println("I hope this empty box make you feel bad for procrastinating: ");
                    System.out.printf("%d.[%s] %s \n", number,
                            curr.getStatusIcon(),
                            curr.getDescription());

                } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    System.out.println("Who uses unmark regularly? Use a more common word like 'erase'.");
                }

            } else {
                list[counter] = new Task(input);
                Squiddy.counter++;

                System.out.println("Let me write this down: " + input);
            }
            System.out.println("—".repeat(50));

            input = myScanner.nextLine().trim().toLowerCase();
            System.out.println("—".repeat(50));
        }

        System.out.println("Bye. Please don't bother me again.");
        System.out.println("—".repeat(50));
    }
}
