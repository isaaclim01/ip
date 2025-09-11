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

    public Squiddy() {
        this("data/squid.txt");
    }

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

    //Starts up by loading storage
    public void start() throws FileNotFoundException, FileCorruptedException{
        storage.start();
        storage.loadFile(tasks);
    }

    public void setIsExit(boolean exit) {
        isExit = exit;
    }

    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        while (!isExit) {
            try {
                String userInput = input.toLowerCase();

                Command c = parser.getCommand(userInput);
                String response = c.execute(userInput, ui, storage, tasks);
                isExit = c.isExit();

                return response;
            } catch (UnknownInputException | FileNotFoundException | FileCorruptedException e) {

                String errorMsg = handler.handleError(e);
                return errorMsg;
            }
        }
        return "";
    }
}
