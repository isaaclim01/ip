package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.main.Storage;
import ip.ui.Ui;

public class ExitCommand implements Command{

    @Override
    public void execute(String input, Ui ui, Storage storage) throws UnknownInputException {
        if (!input.equals("bye")) {
            throw new UnknownInputException("Just enter 'bye' by itself");
        }

        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
