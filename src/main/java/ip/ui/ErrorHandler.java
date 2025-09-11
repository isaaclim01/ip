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
    public String handleError(Exception e) {
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
            return ui.showUnknownInputError(msg);

        case NOT_FOUND:
            storage.start();
            return ui.showFileNotFoundError(msg);

        case CORRUPT:
            return ui.showRefuseRemake();

        case NONE:
            return ui.showOtherError(e.getMessage());

        default:
            return "This is not supposed to happen!";
        }
    }

    public enum ErrorCode {
        UNKNOWN,
        NOT_FOUND,
        CORRUPT,
        NONE
    }
}
