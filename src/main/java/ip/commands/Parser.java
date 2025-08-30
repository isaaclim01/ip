package ip.commands;

public class Parser {

    public Command getCommand(String input) {
        return CommandType.findCommand(input).getCommand();
    }
}
