package ip.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import ip.Squiddy;
import ip.tasks.Task;
import ip.tasks.TaskList;

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
    public String showWelcome() {
        return "I'm Squiddy, forced to be text in your terminal. "
                + "\nType a task and I'll decide if I want to help you remember it";
    }

    //Shows exit message
    public String showExit() {
        return "Bye. Please don't bother me again.";
    }

    //Shows the divider line
    public String showDivider() {
        if (!Squiddy.getIsTestMode()) {
            return DIVIDER;
        }

        return "";
    }

    //Reads user input and returns it as a string
    public String readCommand() {
        String userInput = input.nextLine().trim();

        return userInput;
    }

    //Shows error message for unknown input
    public String showUnknownInputError(String msg) {
        return "You can't do that: " + msg;
    }

    //Shows error message for file not found
    public String showFileNotFoundError(String msg) {
        return "Oh man: " + msg;
    }

    //Shows error message for file corruption
    public String showFileCorruptedError(String msg) {
        if (msg.equals("")) {
            return "Remake file? (y/n)";
        } else {
            return "Squiddy is corrupted: " + msg
                    + "\nRemake file? (y/n)";
        }
    }

    //Shows a message when user refuses to remake file
    public String showRefuseRemake() {
        return "Please fix file manually";
    }

    //Shows error message for other errors
    public String showOtherError(String msg) {
        return "Error not supported: " + msg;
    }

    //Shows task details after executing command
    public String showTaskDetails(Task task) {
        return task.toString().indent(8);
    }

    //Shows message after task input
    public String showTaskInput(Task task) {
        return "Let me write this down:"
                + showTaskDetails(task);
    }

    //Shows message after delete command
    public String showDeleteCommand(Task task, int size) {
        String message = "Removing this task: " + showTaskDetails(task);
        String taskNumber = String.format("You have %d tasks recorded", size);

        return message + taskNumber;
    }

    //Shows list of tasks
    public String showListContent(Task task, int index) {
        String taskString = String.format("%d. %s", index, task.toString()).indent(8);
        return taskString;
    }

    //Shows a message after mark command
    public String showMark(Task task) {
        return "OK, you've completed this: " + showTaskDetails(task);
    }

    //Shows a message after unmark command
    public String showUnmark(Task task) {
        return "Why have you not completed this: " + showTaskDetails(task);
    }

    //Shows a message for repeat command
    public String showRepeat(String repeat) {
        return String.format("What would you like me to do with '%s'?", repeat);
    }

    //Shows a message for test mode
    public String showTestMode() {
        if (Squiddy.getIsTestMode()) {
            return "Switched to test mode and removed the horizontal lines";
        } else {
            return "Turned off test mode";
        }
    }

    //Shows a message for find results
    public String showFindCommand(TaskList results) {
        String title = String.format("I found %d tasks:", results.size());
        StringBuilder message = new StringBuilder(title);
        int index = 1;
        for (Task task : results) {
            String result = "\n" + showListContent(task, index++);
            message.append(result);
        }
        return message.toString();
    }

    //Shows a message when there are no results
    public String showNoResult(String keyword) {
        String title = String.format("There are no tasks containing '%s'", keyword);
        return title + "Search using a better keyword";
    }

    //Returns the current tasks in the TaskList
    public String showListCommand(TaskList tasks) {
        String title = "Let's see what you've got: ";
        StringBuilder message = new StringBuilder(title);

        int max = tasks.size();
        for (int i = 0; i < max; i++) {
            Task curr = tasks.get(i);
            String result = "\n" + showListContent(curr, i + 1);
            message.append(result);
        }
        return message.toString();
    }
}
