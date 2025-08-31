package ip;

import java.io.FileNotFoundException;

import ip.commands.Command;
import ip.commands.Parser;
import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.FileStorage;
import ip.storage.Storage;
import ip.tasks.TaskList;
import ip.ui.ErrorHandler;
import ip.ui.Ui;

public class Squiddy {

    private static boolean isTestMode = false;

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    private final ErrorHandler handler;
    private final TaskList tasks;
    private boolean isExit;

    public Squiddy(String dataPath) {
        this.ui = new Ui();
        this.storage = new FileStorage(dataPath);
        this.parser = new Parser();
        this.handler = new ErrorHandler(storage, ui);
        isExit = false;
        this.tasks = new TaskList();
    }

    public static void setIsTestMode() {
        isTestMode = !isTestMode;
    }

    public static boolean getIsTestMode() {
        return isTestMode;
    }

    public static void main(String[] args) {
        new Squiddy("data/squid.txt").run();
    }

    //Starts up by loading storage
    public void start() {
        storage.start();

        try {
            storage.loadFile(tasks);
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
                c.execute(userInput, ui, storage, tasks);
                isExit = c.isExit();
            } catch (UnknownInputException | FileNotFoundException | FileCorruptedException e) {
                boolean result = handler.handleError(e);
                isExit = !result;
            } finally {
                ui.showDivider();
            }
        }
    }
}
