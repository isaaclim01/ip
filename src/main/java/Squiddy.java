import java.util.Scanner;

public class Squiddy {

    public static String[] list = new String[100];
    public static int counter = 0;

    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("—".repeat(50));
        System.out.println("Hello! I'm Squiddy \nWhat can I do for you?");
        System.out.println("—".repeat(50));

        String input = myScanner.nextLine();
        System.out.println("—".repeat(50));

        while (!input.equals("bye")) {

            if (input.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.printf("%d. %s \n", i + 1, list[i]);
                }

                System.out.println("—".repeat(50));

                input = myScanner.nextLine();
                System.out.println("—".repeat(50));

            } else {
                list[counter] = input;
                Squiddy.counter++;

                System.out.println("added: " + input);
                System.out.println("—".repeat(50));

                input = myScanner.nextLine();
                System.out.println("—".repeat(50));
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("—".repeat(50));
    }
}
