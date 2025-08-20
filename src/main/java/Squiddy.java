import java.util.Scanner;

public class Squiddy {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        System.out.println("—".repeat(50));
        System.out.println("Hello! I'm Squiddy \nWhat can I do for you?");
        System.out.println("—".repeat(50));

        String input = myScanner.nextLine();
        System.out.println("—".repeat(50));

        while (!input.equals("bye")) {
            System.out.println(input);
            System.out.println("—".repeat(50));
            input = myScanner.nextLine();
            System.out.println("—".repeat(50));
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("—".repeat(50));
    }
}
