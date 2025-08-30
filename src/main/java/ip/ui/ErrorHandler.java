package ip.ui;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.main.Storage;

import java.io.FileNotFoundException;

public class ErrorHandler {

    private final Storage storage;
    private final Ui ui;

    public enum ERROR_CODE {
        UNKNOWN,
        NOT_FOUND,
        CORRUPT,
        NONE
    }

    public ErrorHandler(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    //Handling of any exceptions
    public boolean handleError(Exception e) {
        String msg = e.getMessage();
        ERROR_CODE code = ERROR_CODE.NONE;
        if (e instanceof UnknownInputException) {
            code = ERROR_CODE.UNKNOWN;
        } else if (e instanceof FileCorruptedException) {
            code = ERROR_CODE.CORRUPT;
        } else if (e instanceof FileNotFoundException) {
            code = ERROR_CODE.NOT_FOUND;
        }

        switch (code) {
        case UNKNOWN:
            ui.showUnknownInputError(msg);
            return true;

        case NOT_FOUND:
            ui.showFileNotFoundError(msg);
            storage.start();
            return true;

        case CORRUPT:
            ui.showFileCorruptedError(msg);
            String remake = ui.readCommand();

            while (!remake.equals("y") && !remake.equals("n")) {
                ui.showFileCorruptedError("");
                remake = ui.readCommand();
            }

            if (remake.equals("y")) {
                return storage.remakeFile();
            } else {
                ui.showRefuseRemake();
                return false;
            }

        case NONE:
            ui.showOtherError(e.getMessage());
            return false;
        }

        return false;
    }
}
