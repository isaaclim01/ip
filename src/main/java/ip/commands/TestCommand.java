package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.main.Squiddy;
import ip.main.Storage;
import ip.ui.Ui;

public class TestCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage) throws UnknownInputException {
        if (!input.equals("test")) {
            throw new UnknownInputException("Just enter 'test' by itself");
        }

        Squiddy.IS_TEST_MODE = !Squiddy.IS_TEST_MODE;
        if (Squiddy.IS_TEST_MODE) {
            System.out.println("Switched to test mode and removed the horizontal lines");
        } else {
            System.out.println("Turned off test mode");
        }
    }
}
