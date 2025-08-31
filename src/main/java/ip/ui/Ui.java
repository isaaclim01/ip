package ip.ui;

import ip.Squiddy;
import ip.tasks.Task;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "-".repeat(50);
    private final Scanner input;
    private final PrintStream output;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.input = new Scanner(in);
        this.output = out;
    }

    //Shows welcome message
    public void showWelcome() {
        output.println("I'm Squiddy, forced to be text in your terminal. "
                + "\nType a task and I'll decide if I want to help you remember it");
    }

    //Shows exit message
    public void showExit() {
        output.println("Bye. Please don't bother me again.");
    }

    //Shows the divider line
    public void showDivider() {
        if (!Squiddy.getIsTestMode()) {
            output.println(DIVIDER);
        }
    }

    //Reads user input and returns it as a string
    public String readCommand() {
        String userInput = input.nextLine().trim();

        return userInput;
    }

    //Shows error message for unknown input
    public void showUnknownInputError(String msg) {
        output.println("You can't do that: " + msg);
    }

    //Shows error message for file not found
    public void showFileNotFoundError(String msg) {
        output.println("Oh man: " + msg);
    }

    //Shows error message for file corruption
    public void showFileCorruptedError(String msg) {
        if (msg.equals("")) {
            output.println("Remake file? (y/n)");
        } else {
            output.println("Squiddy is corrupted: " + msg);
            output.println("Remake file? (y/n)");
        }
        showDivider();
    }

    //Shows a message when user refuses to remake file
    public void showRefuseRemake() {
        output.println("Please fix file manually");
    }

    //Shows error message for other errors
    public void showOtherError(String msg) {
        output.println("Error not supported: " + msg);
    }

    //Shows task details after executing command
    public void showTaskDetails(Task task) {
        output.println(task.toString().indent(8));
    }

    //Shows message after task input
    public void showTaskInput(Task task) {
        output.println("Let me write this down:");
        showTaskDetails(task);
    }

    //Shows message after delete command
    public void showDeleteCommand(Task task, int size) {
        output.println("Removing this task: ");
        showTaskDetails(task);
        output.println(String.format("You have %d tasks recorded", size));
    }

    //Shows list of tasks
    public void showListContent(Task task, int index) {
        String taskString = String.format("%d. %s", index, task.toString()).indent(8);
        output.println(taskString);
    }

    //Shows a message after mark command
    public void showMark(Task task) {
        output.println("OK, you've completed this: ");
        showTaskDetails(task);
    }

    //Shows a message after unmark command
    public void showUnmark(Task task) {
        output.println("Why have you not completed this: ");
        showTaskDetails(task);
    }

    //Shows a message for repeat command
    public void showRepeat(String repeat) {
        output.println(String.format("What would you like me to do with '%s'?", repeat));
    }

    //Shows a message for test mode
    public void showTestMode() {
        if (Squiddy.getIsTestMode()) {
            output.println("Switched to test mode and removed the horizontal lines");
        } else {
            output.println("Turned off test mode");
        }
    }

    //Shows a message before displaying List
    public void showListHeader() {
        output.println("Let's see what you've got: ");
    }
}
