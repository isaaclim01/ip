package ip.commands;

import ip.main.Storage;
import ip.ui.Ui;

public class RepeatCommand implements Command {
    @Override
    public void execute(String input, Ui ui, Storage storage) {
        System.out.println(String.format("What would you like me to do with '%s'?", input));
    }
}
