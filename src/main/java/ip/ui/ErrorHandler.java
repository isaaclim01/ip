package ip.ui;

import java.io.FileNotFoundException;

import ip.exceptions.FileCorruptedException;
import ip.exceptions.UnknownInputException;
import ip.storage.Storage;

public class ErrorHandler {

    private final Storage storage;
    private final Ui ui;

    public ErrorHandler(Storage storage, Ui ui) {
        this.storage = storage;
        this.ui = ui;
    }

    //Handling of any exceptions
    public boolean handleError(Exception e) {
        String msg = e.getMessage();
        ErrorCode code = ErrorCode.NONE;
        if (e instanceof UnknownInputException) {
            code = ErrorCode.UNKNOWN;
        } else if (e instanceof FileCorruptedException) {
            code = ErrorCode.CORRUPT;
        } else if (e instanceof FileNotFoundException) {
            code = ErrorCode.NOT_FOUND;
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

        default:
            return false;
        }
    }

    public enum ErrorCode {
        UNKNOWN,
        NOT_FOUND,
        CORRUPT,
        NONE
    }
}
