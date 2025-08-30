package ip.main;

import ip.commands.Command;
import ip.commands.Parser;
import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.tasks.Task;
import ip.ui.ErrorHandler;
import ip.ui.Ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Squiddy {

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static boolean IS_TEST_MODE = false;

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final ErrorHandler handler;
    private boolean isExit;

    public Squiddy(String dataPath) {
        this.ui = new Ui();
        this.storage = new Storage(dataPath);
        this.parser = new Parser();
        this.handler = new ErrorHandler(storage, ui);
        isExit = false;
    }

    //Starts up by loading storage
    public void start() {
        storage.start();

        try {
            storage.loadFile();
        } catch (FileNotFoundException | FileCorruptedException e) {
            boolean result = handler.handleError(e);
            isExit = !result;
        } finally {
            ui.showDivider();
        }
    }

    //Runs the actual program
    public void run() {
        isExit = false;
        start();
        if (!isExit) {
            ui.showWelcome();
        } else {
            ui.showExit();
        }
        ui.showDivider();

        while (!isExit) {
            try {
                String userInput = ui.readCommand().toLowerCase();
                ui.showDivider();
                Command c = parser.getCommand(userInput);
                c.execute(userInput, ui, storage);
                isExit = c.isExit();
            } catch (UnknownInputException | FileNotFoundException | FileCorruptedException e) {
                boolean result = handler.handleError(e);
                isExit = !result;
            } finally {
                ui.showDivider();
            }
        }
    }

    public static void main(String[] args) {
        new Squiddy("data/squid.txt").run();
    }
}
